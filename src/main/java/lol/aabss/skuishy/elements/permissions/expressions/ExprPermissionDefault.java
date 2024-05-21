package lol.aabss.skuishy.elements.permissions.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Permissions - Permission Default")
@Description("Gets/Sets the permission default of a permission.")
@Examples({
        "send permission default of {_p}"
})
@Since("2.1")
public class ExprPermissionDefault extends SimpleExpression<PermissionDefault> {

    static{
        Skript.registerExpression(ExprPermissionDefault.class, PermissionDefault.class, ExpressionType.COMBINED,
                "[the] [permission] default [value] of %bukkitpermission%",
                "%bukkitpermission%'s [permission] default [value]"
        );
    }

    private Expression<Permission> perm;

    @Override
    protected PermissionDefault @NotNull [] get(@NotNull Event event) {
        Permission perm = this.perm.getSingle(event);
        if (perm != null) {
            return new PermissionDefault[]{perm.getDefault()};
        }
        return new PermissionDefault[0];
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(PermissionDefault.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            Permission perm = this.perm.getSingle(event);
            if (perm != null) {
                perm.setDefault((PermissionDefault) delta[0]);
            }
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends PermissionDefault> getReturnType() {
        return PermissionDefault.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "permission default of permission";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        perm = (Expression<Permission>) exprs[0];
        return true;
    }
}
