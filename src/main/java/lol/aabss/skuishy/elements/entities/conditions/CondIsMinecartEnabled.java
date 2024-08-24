package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.HopperMinecart;
import org.jetbrains.annotations.NotNull;

@Name("Minecart - Is Enabled")
@Description("True if the hopper minecart is enabled.")
@Examples({
        "if {_minecart} is not hopper enabled:",
        "\tset enabled state of {_minecart} to true"
})
@Since("2.8")
public class CondIsMinecartEnabled extends PropertyCondition<Entity> {

    static {
        register(CondIsMinecartEnabled.class, "(hopper|minecart) enabled", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof HopperMinecart) {
            return ((HopperMinecart) entity).isEnabled();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "minecart enabled";
    }
}