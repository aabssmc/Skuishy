package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand - Is Marker")
@Description("True if the armorstand is marker.")
@Examples({
        "if {_armorstand} is marker:",
        "\tset marker state of {_armorstand} to false"
})
@Since("2.8")
public class CondIsMarker extends PropertyCondition<Entity> {

    static {
        register(CondIsMarker.class, PropertyType.HAVE, "[armor[ |-]stand] marker", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).isMarker();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "marker";
    }
}
