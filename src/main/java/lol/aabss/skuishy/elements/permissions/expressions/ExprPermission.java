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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

import org.bukkit.permissions.Permission;

import static lol.aabss.skuishy.Skuishy.last_permission;

@Name("Permissions - Permission")
@Description("Gets or makes a permission by it's name.")
@Examples({
        "send all users with permission named \"skuishy.command.info\""
})
@Since("2.1")
public class ExprPermission extends SimpleExpression<Permission> {

    static {
        Skript.registerExpression(ExprPermission.class, Permission.class, ExpressionType.COMBINED,
                "[a] [:new] permission (named|(by|with) name) %string%"
        );
    }

    private Expression<String> name;
    private boolean neww;

    @Override
    protected Permission @NotNull [] get(@NotNull Event e) {
        String name = this.name.getSingle(e);
        if (name != null) {
            Permission perm = new Permission(name);
            if (neww) {
                Bukkit.getPluginManager().addPermission(perm);
                last_permission = perm;
            }
            return new Permission[]{perm};
        }
        return new Permission[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Permission> getReturnType() {
        return Permission.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "permission";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        neww = parseResult.hasTag("new");
        return true;
    }
}
