package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Rabbit - More Carrot Ticks")
@Description("Gets/sets the more carrot ticks of a rabbit.")
@Examples({
        "set more carrot ticks of {_rabbit} to 20 # 1 second"
})
@Since("2.8")
public class ExprRabbitCarrotTicks extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprRabbitCarrotTicks.class, Integer.class, "[rabbit] more carrot ticks", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "more carrot ticks";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof Rabbit) {
            return ((Rabbit) entity).getMoreCarrotTicks();
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
                    if (entity instanceof Rabbit) {
                        ((Rabbit) entity).setMoreCarrotTicks((Integer) delta[0]);
                    }
                }
            }
        }
    }
}