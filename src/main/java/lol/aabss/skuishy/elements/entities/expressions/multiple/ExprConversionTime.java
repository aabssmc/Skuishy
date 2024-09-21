package lol.aabss.skuishy.elements.entities.expressions.multiple;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.Nullable;

@Name("Skeleton/Zombie/Hoglin - Conversion Time")
@Description("Gets/sets the conversion time of a skeleton, zombie or hoglin.")
@Examples({
        "set conversion time of {_zombie} to 3"
})
@Since("2.8")
public class ExprConversionTime extends EntityExpression<Entity, Integer> {

    static {
        register(ExprConversionTime.class, Integer.class, "[skeleton|zombie|hoglin] conversion time", "entities");
    }

    @Override
    public Integer get(Entity entity) {
        if (entity instanceof Skeleton) {
            return ((Skeleton) entity).getConversionTime();
        } else if (entity instanceof Zombie) {
            return ((Zombie) entity).getConversionTime();
        } else if (entity instanceof Hoglin) {
            return ((Hoglin) entity).getConversionTime();
        }
        return null;
    }

    @Override
    public void change(Entity entity, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof Skeleton) {
                ((Skeleton) entity).setConversionTime(integer);
            } else if (entity instanceof Zombie) {
                ((Zombie) entity).setConversionTime(integer);
            } else if (entity instanceof Hoglin) {
                ((Hoglin) entity).setConversionTime(integer);
            }
        }
    }
}