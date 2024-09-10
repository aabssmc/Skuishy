package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.TNTPrimed;
import org.jetbrains.annotations.Nullable;

@Name("Tnt/Minecart/Creeper - Fuse Ticks")
@Description("Gets/sets the fuse ticks of a primed tnt, explosive minecart or creeper.")
@Examples({
        "set fuse ticks of {_tnt} to 10"
})
@Since("2.8")
public class ExprTntFuseTicks extends EntityExpression<TNTPrimed, Integer> {

    static {
        register(ExprTntFuseTicks.class, Integer.class, "[([primed[-| ]] tnt|minecart)] fuse ticks", "entities");
    }

    @Override
    public Integer get(TNTPrimed tntPrimed) {
        return tntPrimed.getFuseTicks();
    }

    @Override
    public void change(TNTPrimed tntPrimed, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            tntPrimed.setFuseTicks(integer);
        }
    }
}
