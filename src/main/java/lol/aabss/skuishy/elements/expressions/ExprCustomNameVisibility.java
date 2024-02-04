package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("Entity - Custom Name Visibility")
@Description("Gets/sets the custom name visibility of entities.")
@Examples({
        "set custom name visibility of target entity to true"
})
@Since("1.7.5")
public class ExprCustomNameVisibility extends PropertyExpression<Entity, Boolean> {

    static {
        register(ExprCustomNameVisibility.class, Boolean.class,
                "custom[ ]name visib(le|ility)",
                "entities"
        );
    }

    @Override
    protected @Nullable Boolean[] get(@NonNull Event e, Entity @NonNull [] source) {
        Entity en = getExpr().getSingle(e);
        if (en != null) return new Boolean[]{en.isCustomNameVisible()};
        return new Boolean[]{};
    }

    @Override
    public void change(@NonNull Event e, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            Entity en = getExpr().getSingle(e);
            if (en != null) {
                en.setCustomNameVisible((Boolean) delta[0]);
            }
        }
        else {
            assert false;
        }
    }

    @Override
    public Class<?> @NonNull [] acceptChange(final Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public @NonNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "custom name visibility";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }
}
