package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Skeleton/Zombie - Conversion Time")
@Description("Gets/sets the conversion time of a skeleton or zombie.")
@Examples({
        "set conversion time of {_zombie} to 3"
})
@Since("2.8")
public class ExprConversionTime extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprConversionTime.class, Integer.class, "[skeleton|zombie] conversion time", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "conversion time";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof Skeleton) {
            if (((Skeleton) entity).isConverting()) {
                return ((Skeleton) entity).getConversionTime();
            }
        } else if (entity instanceof Zombie) {
            if (((Zombie) entity).isConverting()) {
                return ((Zombie) entity).getConversionTime();
            }
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
                    if (entity instanceof Skeleton) {
                        ((Skeleton) entity).setConversionTime((Integer) delta[0]);
                    } else if (entity instanceof Zombie) {
                        ((Zombie) entity).setConversionTime((Integer) delta[0]);
                    }
                }
            }
        }
    }
}