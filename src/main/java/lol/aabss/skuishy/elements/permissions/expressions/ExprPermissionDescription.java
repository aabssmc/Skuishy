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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Permissions - Permission Description")
@Description("Gets/Sets the permission description of a permission.")
@Examples({
        "send permission description of {_p}"
})
@Since("2.1")
public class ExprPermissionDescription extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprPermissionDescription.class, String.class, ExpressionType.COMBINED,
                "[the] ([permission] description|description [permission]) [value] of %permission%",
                "%permission%'s ([permission] description|description [permission]) [value]"
        );
    }

    private Expression<Permission> perm;

    @Override
    protected String @NonNull [] get(@NonNull Event e) {
        Permission perm = this.perm.getSingle(e);
        if (perm != null) {
            return new String[]{perm.getDescription()};
        }
        return new String[0];
    }

    @Override
    public Class<?> [] acceptChange(Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

    @Override
    public void change(@NonNull Event e, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            if (delta != null) {
                Permission perm = this.perm.getSingle(e);
                if (perm != null) {
                    perm.setDescription((String) delta[0]);
                }
            }
        }
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
        return "permission description of permission";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        perm = (Expression<Permission>) exprs[0];
        return true;
    }
}
