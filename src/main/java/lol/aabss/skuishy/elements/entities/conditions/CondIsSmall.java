package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.ArmorStand;

@Name("Armor Stand - Is Small")
@Description("True if the armorstand is small.")
@Examples({
        "if {_armorstand} is small:",
        "\tset small state of {_armorstand} to false"
})
@Since("2.8")
public class CondIsSmall extends EntityCondition<ArmorStand> {

    static {
        register(CondIsSmall.class, PropertyType.HAVE, "[armor[ |-]stand] small", "entities");
    }

    @Override
    protected boolean run(ArmorStand armorStand) {
        return armorStand.isSmall();
    }

}
