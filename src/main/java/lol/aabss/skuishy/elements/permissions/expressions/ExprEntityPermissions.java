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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Permissions - All Permissions")
@Description("Gets the permissions of a permission attachment or all the permissions registered.")
@Examples({
        "send permissions of last permission attachment",
        "send all permissions"
})
@Since("2.1")
public class ExprEntityPermissions extends SimpleExpression<Permission> {

    static {
        Skript.registerExpression(ExprEntityPermissions.class, Permission.class, ExpressionType.COMBINED,
                "[all [[of] the]] permissions [of %-permissionattachment%]",
                "%permissionattachment%'s permissions"
        );
    }

    private Expression<PermissionAttachment> attach;

    @Override
    protected Permission @NotNull [] get(@NotNull Event e) {
        PermissionAttachment attach = this.attach.getSingle(e);
        if (attach != null){
            List<Permission> perms = new ArrayList<>();
            for (String perm : attach.getPermissions().keySet()) {
                perms.add(new Permission(perm));
            } return perms.toArray(Permission[]::new);
        }
        return Bukkit.getPluginManager().getPermissions().toArray(Permission[]::new);
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.REMOVE_ALL) {
            return CollectionUtils.array(Permission.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD){
            Bukkit.getPluginManager().addPermission((Permission) delta[0]);
        } else if (mode == Changer.ChangeMode.REMOVE){
            Bukkit.getPluginManager().removePermission((Permission) delta[0]);
        } else if (mode == Changer.ChangeMode.REMOVE_ALL){
            Bukkit.getPluginManager().clearPermissions();
        }

        super.change(e, delta, mode);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Permission> getReturnType() {
        return Permission.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "permissions";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        attach = (Expression<PermissionAttachment>) exprs[0];
        return true;
    }
}
