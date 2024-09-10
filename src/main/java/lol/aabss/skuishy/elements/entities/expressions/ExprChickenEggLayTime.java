package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Chicken;
import org.jetbrains.annotations.Nullable;

@Name("Chicken - Egg Lay Time")
@Description("Gets/sets the egg lay time of a chicken in ticks.")
@Examples({
        "set egg lay time of {_chicken} to 20 # 1 second"
})
@Since("2.8")
public class ExprChickenEggLayTime extends EntityExpression<Chicken, Integer> {

    static {
        register(ExprChickenEggLayTime.class, Integer.class, "[chicken] egg lay time", "entities");
    }

    @Override
    public Integer get(Chicken chicken) {
        return chicken.getEggLayTime();
    }

    @Override
    public void change(Chicken chicken, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            chicken.setEggLayTime(integer);
        }
    }

}