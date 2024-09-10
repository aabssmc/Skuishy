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
public class ExprMinecartPush extends EntityExpression<PoweredMinecart, Double> {

    static {
        register(ExprMinecartPush.class, Double.class, "[minecart] push (:x|z)", "entities");
    }

    @Override
    public Double get(PoweredMinecart poweredMinecart) {
        return tags.contains("x") ? poweredMinecart.getPushX() : poweredMinecart.getPushZ();
    }

    @Override
    public void change(PoweredMinecart poweredMinecart, @Nullable Double aDouble, Changer.ChangeMode mode) {
        if (aDouble != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("x")) {
                poweredMinecart.setPushX(aDouble);
            } else {
                poweredMinecart.setPushZ(aDouble);
            }
        }
    }
}