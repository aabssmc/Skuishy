package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Piglin;

@Name("Piglin - Charging Crossbow")
@Description("Gets/sets the charging crossbow state of a piglin.")
@Examples({
        "set charging crossbow state of {_piglin} to false"
})
@Since("2.8")
public class ExprPiglinChargingCrossbow extends EntityExpression<Piglin, Boolean> {

    static {
        register(ExprPiglinChargingCrossbow.class, Boolean.class, "[piglin] charging crossbow [state|mode]", "entities");
    }

    @Override
    public Boolean get(Piglin piglin) {
        return piglin.isChargingCrossbow();
    }

    @Override
    public void change(Piglin piglin, Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            piglin.setChargingCrossbow(aBoolean);
        }
    }
}
