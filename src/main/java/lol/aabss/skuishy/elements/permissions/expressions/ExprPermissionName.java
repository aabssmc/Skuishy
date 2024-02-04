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
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Permissions - Permission Name")
@Description("Gets the permission name of a permission.")
@Examples({
        "send permission name of {_p}"
})
@Since("2.1")
public class ExprPermissionName extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprPermissionName.class, String.class, ExpressionType.COMBINED,
                "[the] ([permission] name|name [permission]) [value] of %permission%",
                "%permission%'s ([permission] name|name [permission]) [value]"
        );
    }

    private Expression<Permission> perm;

    @Override
    protected String @NonNull [] get(@NonNull Event e) {
        Permission perm = this.perm.getSingle(e);
        if (perm != null) {
            return new String[]{perm.getName()};
        }
        return new String[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "permission name of permission";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        perm = (Expression<Permission>) exprs[0];
        return true;
    }
}
