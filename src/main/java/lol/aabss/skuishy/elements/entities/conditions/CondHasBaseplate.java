package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand - Has BasePlate")
@Description("True if the armorstand has a baseplate.")
@Examples({
        "if {_armorstand} has baseplate:",
        "\tset baseplate state of {_armorstand} to false"
})
@Since("2.8")
public class CondHasBaseplate extends PropertyCondition<Entity> {

    static {
        register(CondHasBaseplate.class, PropertyType.HAVE, "[armor[ |-]stand] baseplate", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).hasBasePlate();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "baseplate";
    }
}
