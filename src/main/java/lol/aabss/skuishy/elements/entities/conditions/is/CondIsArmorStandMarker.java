package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.ArmorStand;

@Name("Armor Stand - Is Marker")
@Description("True if the armorstand is marker.")
@Examples({
        "if {_armorstand} is marker:",
        "\tset marker state of {_armorstand} to false"
})
@Since("2.8")
public class CondIsArmorStandMarker extends EntityCondition<ArmorStand> {

    static {
        register(CondIsArmorStandMarker.class, PropertyType.HAVE, "[armor[ |-]stand] marker", "entities");
    }

    @Override
    public boolean run(ArmorStand armorStand) {
        return armorStand.isMarker();
    }

}
