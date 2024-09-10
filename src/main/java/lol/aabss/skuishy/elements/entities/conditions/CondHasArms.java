package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.ArmorStand;

@Name("Armor Stand - Has Arms")
@Description("True if the armorstand has arms.")
@Examples({
        "if {_armorstand} has arms:",
        "\tset arms state of {_armorstand} to false"
})
@Since("2.8")
public class CondHasArms extends EntityCondition<ArmorStand> {

    static {
        register(CondHasArms.class, PropertyType.HAVE, "[armor[ |-]stand] arms", "entities");
    }

    @Override
    protected boolean run(ArmorStand armorStand) {
        return armorStand.hasArms();
    }

}
