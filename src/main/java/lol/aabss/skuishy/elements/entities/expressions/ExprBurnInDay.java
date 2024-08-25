package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.AbstractSkeleton;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Skeleton/Zombie - Burn In Day")
@Description("Gets/sets the burn in day state of a skeleton or zombie.")
@Examples({
        "set burn in day state of {_skeleton} to true"
})
@Since("2.8")
public class ExprBurnInDay extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprBurnInDay.class, Boolean.class, "[skeleton|zombie] [should] burn in day [state|mode]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "burn in day";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof AbstractSkeleton) {
            return ((AbstractSkeleton) entity).shouldBurnInDay();
        } else if (entity instanceof Zombie) {
            return ((Zombie) entity).shouldBurnInDay();
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
                    if (entity instanceof AbstractSkeleton) {
                        ((AbstractSkeleton) entity).setShouldBurnInDay((Boolean) delta[0]);
                    } else if (entity instanceof Zombie) {
                        ((Zombie) entity).setShouldBurnInDay((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}