package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.minecart.HopperMinecart;

@Name("Minecart - Is Enabled")
@Description("True if the hopper minecart is enabled.")
@Examples({
        "if {_minecart} is not hopper enabled:",
        "\tset enabled state of {_minecart} to true"
})
@Since("2.8")
public class CondIsMinecartEnabled extends EntityCondition<HopperMinecart> {

    static {
        register(CondIsMinecartEnabled.class, "[hopper] minecart enabled", "entities");
    }

    @Override
    public boolean run(HopperMinecart hopperMinecart) {
        return hopperMinecart.isEnabled();
    }

}