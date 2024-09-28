package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.PolarBear;

@Name("Polar Bear - Standing")
@Description("True if the polar bear is standing.")
@Examples({
        "if {_polarbear} is standing:",
        "\tset polarbear standing of {_polarbear} to false"
})
@Since("2.8")
public class CondIsPolarBearStanding extends EntityCondition<PolarBear> {

    static {
        register(CondIsPolarBearStanding.class, "[polar[ ]bear] standing", "entities");
    }

    @Override
    protected boolean run(PolarBear polarBear) {
        return polarBear.isStanding();
    }

}