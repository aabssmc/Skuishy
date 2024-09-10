package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.ArmorStand;

@Name("Armor Stand - Has BasePlate")
@Description("True if the armorstand has a baseplate.")
@Examples({
        "if {_armorstand} has baseplate:",
        "\tset baseplate state of {_armorstand} to false"
})
@Since("2.8")
public class CondHasBaseplate extends EntityCondition<ArmorStand> {

    static {
        register(CondHasBaseplate.class, PropertyType.HAVE, "[armor[ |-]stand] baseplate", "entities");
    }

    @Override
    protected boolean run(ArmorStand armorStand) {
        return armorStand.hasBasePlate();
    }
}
