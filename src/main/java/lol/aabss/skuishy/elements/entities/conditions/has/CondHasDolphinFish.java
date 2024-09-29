package lol.aabss.skuishy.elements.entities.conditions.has;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Dolphin;

@Name("Dolphin - Has Fish")
@Description("True if the dolphin has a fish:")
@Examples({
        "if {_dolphin} has fish:",
        "\tset fish state of {_dolphin} to false"
})
@Since("2.8")
public class CondHasDolphinFish extends EntityCondition<Dolphin> {

    static {
        register(CondHasDolphinFish.class, PropertyType.HAVE, "[dolphin] fish", "entities");
    }

    @Override
    protected boolean run(Dolphin dolphin) {
        return dolphin.hasFish();
    }

}
