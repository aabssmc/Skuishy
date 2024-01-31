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
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Permissions - Permissible of Permission Attachment")
@Description("Gets the permissible of a permission attachment.")
@Examples({
        "give user of last permission attachment 1 diamond"
})
@Since("2.1")
public class ExprPermissible extends SimpleExpression<Entity> {

    static {
        Skript.registerExpression(ExprPermissible.class, Entity.class, ExpressionType.COMBINED,
                "[the] (permissible|entity|user) of %permissionattachment%",
                "%permissionattachment%'s (permissible|entity|user)"
        );
    }

    private Expression<PermissionAttachment> attach;

    @Override
    protected Entity @NotNull [] get(@NotNull Event e) {
        PermissionAttachment attach = this.attach.getSingle(e);
        if (attach != null){
            return new Entity[]{(Entity) attach.getPermissible()};
        }
        return new Entity[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "permissible of permission attachment";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        attach = (Expression<PermissionAttachment>) exprs[0];
        return true;
    }
}
