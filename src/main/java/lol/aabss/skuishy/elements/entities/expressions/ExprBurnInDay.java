package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.AbstractSkeleton;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.Nullable;

@Name("Skeleton/Zombie/Phantom - Burn In Day")
@Description("Gets/sets the burn in day state of a skeleton, zombie or phantom.")
@Examples({
        "set burn in day state of {_skeleton} to true"
})
@Since("2.8")
public class ExprBurnInDay extends EntityExpression<Entity, Boolean> {

    static {
        register(ExprBurnInDay.class, Boolean.class, "[skeleton|zombie|phantom] [should] burn in day [state|mode]", "entities");
    }

    @Override
    public Boolean get(Entity entity) {
        if (entity instanceof AbstractSkeleton) {
            return ((AbstractSkeleton) entity).shouldBurnInDay();
        } else if (entity instanceof Zombie) {
            return ((Zombie) entity).shouldBurnInDay();
        } else if (entity instanceof Phantom) {
            return ((Phantom) entity).shouldBurnInDay();
        }
        return null;
    }

    @Override
    public void change(Entity entity, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof AbstractSkeleton) {
                ((AbstractSkeleton) entity).setShouldBurnInDay(aBoolean);
            } else if (entity instanceof Zombie) {
                ((Zombie) entity).setShouldBurnInDay(aBoolean);
            } else if (entity instanceof Phantom) {
                ((Phantom) entity).setShouldBurnInDay(aBoolean);
            }
        }
    }
}