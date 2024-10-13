package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.PolarBear;

@Name("Polar Bear - Standing")
@Description("Gets/sets the standing state of a polar bear.")
@Examples({
        "set standing state of {_polarbear} to false"
})
@Since("2.8")
public class ExprPolarBearStanding extends EntityExpression<PolarBear, Boolean> {

    static {
        register(ExprPolarBearStanding.class, Boolean.class, "[polar[ ]bear] standing [state|mode]", "entities");
    }

    @Override
    public Boolean get(PolarBear polarBear) {
        return polarBear.isStanding();
    }

    @Override
    public void change(PolarBear polarBear, Boolean object, Changer.ChangeMode mode) {
        if (object != null && mode == Changer.ChangeMode.SET) {
            polarBear.setStanding(object);
        }
    }
}
