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

@Name("Chicken - Chicken Jockey")
@Description("Gets/sets the chicken jockey state of a chicken.")
@Examples({
        "set chicken jockey state of {_chicken} to true"
})
@Since("2.8")
public class ExprChickenJockey extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprChickenJockey.class, Boolean.class, "chicken jockey [state|mode]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "chicken jockey";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof Chicken) {
            return ((Chicken) entity).isChickenJockey();
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
                    if (entity instanceof Chicken) {
                        ((Chicken) entity).setIsChickenJockey((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}