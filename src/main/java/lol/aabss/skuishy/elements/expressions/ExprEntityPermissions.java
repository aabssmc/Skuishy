package lol.aabss.skuishy.elements.expressions;

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
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Permissions - Permissions of Permission Attachment")
@Description("Gets the permissions of a permission attachment.")
@Examples({
        "send permissions of last permission attachment"
})
@Since("2.1")
public class ExprEntityPermissions extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprEntityPermissions.class, String.class, ExpressionType.COMBINED,
                "[all [[of] the]] permissions of %permissionattachment%",
                "%permissionattachment%'s permissions"
        );
    }

    private Expression<PermissionAttachment> attach;

    @Override
    protected String @NotNull [] get(@NotNull Event e) {
        PermissionAttachment attach = this.attach.getSingle(e);
        if (attach != null){
            List<String> perms = new ArrayList<>(attach.getPermissions().keySet());
            return perms.toArray(String[]::new);
        }
        return new String[0];
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "permissions of permission attachment";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        attach = (Expression<PermissionAttachment>) exprs[0];
        return true;
    }
}
