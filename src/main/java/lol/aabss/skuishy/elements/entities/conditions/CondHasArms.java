package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand - Has Arms")
@Description("True if the armorstand has arms.")
@Examples({
        "if {_armorstand} has arms:",
        "\tset arms state of {_armorstand} to false"
})
@Since("2.8")
public class CondHasArms extends PropertyCondition<Entity> {

    static {
        register(CondHasArms.class, PropertyType.HAVE, "[armor[ |-]stand] arms", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).hasArms();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "arms";
    }
}
