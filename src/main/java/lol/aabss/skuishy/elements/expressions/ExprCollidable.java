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
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Living Entity - Collidable")
@Description("Sets/Gets the collidable state of a living entity.")
@Examples({
        "set collidable state of target entity to true"
})
@Since("2.1")
public class ExprCollidable extends PropertyExpression<LivingEntity, Boolean> {

    static {
        register(ExprCollidable.class, Boolean.class, "collidab(le|ility) [state|mode]", "livingentities");
    }

    @Override
    protected Boolean @NotNull [] get(@NotNull Event event, LivingEntity[] source) {
        for (LivingEntity en : source){
            return new Boolean[]{en.isCollidable()};
        }
        return new Boolean[]{false};
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "collidable of entity";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends LivingEntity>) exprs[0]);
        return true;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            for (LivingEntity en : getExpr().getArray(e)) {
                en.setCollidable((Boolean) delta[0]);
            }
        }
    }
}
