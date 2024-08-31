package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Dolphin - Has Fish")
@Description("Gets/sets the has fish state of a dolphin.")
@Examples({
        "set has fish state of {_dolphin} to true"
})
@Since("2.8")
public class ExprDolphinFish extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprDolphinFish.class, Boolean.class, "[dolphin] [has] fish (state|mode)", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "dolphin fish";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof Dolphin) {
            return ((Dolphin) entity).hasFish();
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
                    if (entity instanceof Dolphin) {
                        ((Dolphin) entity).setHasFish((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}