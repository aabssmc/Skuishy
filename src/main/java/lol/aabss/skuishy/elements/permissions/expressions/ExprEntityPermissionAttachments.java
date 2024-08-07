package lol.aabss.skuishy.elements.permissions.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Permissions - All Permission Attachments")
@Description("Gets the permission attachments of a entity.")
@Examples({
        "send all permission attachments of player"
})
@Since("2.1")
public class ExprEntityPermissionAttachments extends SimpleExpression<PermissionAttachment> {

    static {
        Skript.registerExpression(ExprEntityPermissionAttachments.class, PermissionAttachment.class, ExpressionType.COMBINED,
                "[all [of the]] permission attachments of %entities%",
                "%entities%'s permission attachments"
        );
    }

    private Expression<Entity> entity;

    @Override
    protected PermissionAttachment @NotNull [] get(@NotNull Event event) {
        for (Entity en : entity.getArray(event)){
            List<PermissionAttachment> perms = new ArrayList<>();
            for (PermissionAttachmentInfo perm : en.getEffectivePermissions()){
                perms.add(perm.getAttachment());
            } return perms.toArray(PermissionAttachment[]::new);
        }
        return new PermissionAttachment[0];
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends PermissionAttachment> getReturnType() {
        return PermissionAttachment.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "permission attachments of entity";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        entity = (Expression<Entity>) exprs[0];
        return true;
    }
}
