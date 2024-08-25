package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Chicken - Egg Lay Time")
@Description("Gets/sets the egg lay time of a chicken in ticks.")
@Examples({
        "set egg lay time of {_chicken} to 20 # 1 second"
})
@Since("2.8")
public class ExprChickenEggLayTime extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprChickenEggLayTime.class, Integer.class, "[chicken] egg lay time", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "egg lay time";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof Chicken) {
            return ((Chicken) entity).getEggLayTime();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Integer.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Integer) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Chicken) {
                        ((Chicken) entity).setEggLayTime((Integer) delta[0]);
                    }
                }
            }
        }
    }
}