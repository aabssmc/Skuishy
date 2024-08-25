package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand - Is Small")
@Description("True if the armorstand is small.")
@Examples({
        "if {_armorstand} is small:",
        "\tset small state of {_armorstand} to false"
})
@Since("2.8")
public class CondIsSmall extends PropertyCondition<Entity> {

    static {
        register(CondIsSmall.class, PropertyType.HAVE, "[armor[ |-]stand] small", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).isSmall();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "small";
    }
}
