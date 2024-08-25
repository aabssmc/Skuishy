package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand - Is Visible")
@Description("True if the armorstand is visible.")
@Examples({
        "if {_armorstand} is visible:",
        "\tset visible state of {_armorstand} to false"
})
@Since("2.8")
public class CondIsVisible extends PropertyCondition<Entity> {

    static {
        register(CondIsVisible.class, PropertyType.HAVE, "armor[ |-]stand visible", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).isVisible();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "visible";
    }
}
