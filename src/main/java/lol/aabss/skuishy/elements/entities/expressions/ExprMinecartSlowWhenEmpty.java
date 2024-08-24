package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Slow When Empty")
@Description("Gets/sets the slow when empty state of a minecart.")
@Examples({
        "set slow when empty of {_minecart} to false"
})
@Since("2.8")
public class ExprMinecartSlowWhenEmpty extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprMinecartSlowWhenEmpty.class, Boolean.class, "[minecart] slow when empty [mode|state]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "slow when empty";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof Minecart) {
            return ((Minecart) entity).isSlowWhenEmpty();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Boolean) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Minecart) {
                        ((Minecart) entity).setSlowWhenEmpty((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}
