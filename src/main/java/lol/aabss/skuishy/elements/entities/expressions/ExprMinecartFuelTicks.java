package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Fuel Ticks")
@Description("Gets/sets the fuel ticks of a powered minecart.")
@Examples({
        "set fuel ticks of {_minecart} to 20 # 1 second"
})
@Since("2.8")
public class ExprMinecartFuelTicks extends EntityExpression<PoweredMinecart, Integer> {

    static {
        register(ExprMinecartFuelTicks.class, Integer.class, "[minecart] fuel [ticks]", "entities");
    }

    @Override
    public Integer get(PoweredMinecart poweredMinecart) {
        return poweredMinecart.getFuel();
    }

    @Override
    public void change(PoweredMinecart poweredMinecart, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            poweredMinecart.setFuel(integer);
        }
    }
}