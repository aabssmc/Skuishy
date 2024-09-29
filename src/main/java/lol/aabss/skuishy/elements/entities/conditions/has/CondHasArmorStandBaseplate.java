package lol.aabss.skuishy.elements.entities.conditions.has;

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
public class CondHasArmorStandBaseplate extends EntityCondition<ArmorStand> {

    static {
        register(CondHasArmorStandBaseplate.class, PropertyType.HAVE, "[armor[ |-]stand] baseplate", "entities");
    }

    @Override
    protected boolean run(ArmorStand armorStand) {
        return armorStand.hasBasePlate();
    }
}
