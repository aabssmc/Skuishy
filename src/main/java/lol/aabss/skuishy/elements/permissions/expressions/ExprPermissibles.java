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
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Permissions - All Permissibles")
@Description("Gets all the permissibles that have a permission.")
@Examples({
        "send all users with permission named \"skuishy.command.info\""
})
@Since("2.1")
public class ExprPermissibles extends SimpleExpression<Entity> {

    static {
        Skript.registerExpression(ExprPermissibles.class, Entity.class, ExpressionType.COMBINED,
                "[all [of the]] (permissibles|entit(ies|ys)|users) (with|that have) %bukkitpermission%"
        );
    }

    private Expression<Permission> perm;

    @Override
    protected Entity @NotNull [] get(@NotNull Event e) {
        Permission perm = this.perm.getSingle(e);
        if (perm != null){
            return (Entity[]) perm.getPermissibles().toArray(Permissible[]::new);
        }
        return new Entity[0];
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "all permissibles with permission";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        perm = (Expression<Permission>) exprs[0];
        return true;
    }
}
