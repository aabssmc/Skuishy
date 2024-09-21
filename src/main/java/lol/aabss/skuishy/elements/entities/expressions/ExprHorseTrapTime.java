package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.SkeletonHorse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Trap Time")
@Description("Gets/sets the trap time of a horse.")
@Examples({
        "set trapped time of {_horse} to true"
})
@Since("2.8")
public class ExprHorseTrapTime extends EntityExpression<SkeletonHorse, Integer> {

    static {
        register(ExprHorseTrapTime.class, Integer.class, "[horse] trap[ped] time", "entities");
    }

    @Override
    public Integer get(SkeletonHorse horse) {
        return horse.getTrapTime();
    }

    @Override
    public void change(SkeletonHorse horse, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            horse.setTrapTime(integer);
        }
    }
}