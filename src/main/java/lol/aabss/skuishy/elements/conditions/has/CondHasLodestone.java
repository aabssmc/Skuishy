package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

@Name("ItemStack - Has Lodestone")
@Description("Returns true if the item has lodestone.")
@Examples({
        "if {_i} has lodestone:"
})
@Since("2.0")
public class CondHasLodestone extends PropertyCondition<ItemStack> {

    static {
        register(CondHasLodestone.class, PropertyType.HAVE, "[a] lodestone", "itemstacks");
    }

    @Override
    public boolean check(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta instanceof CompassMeta){
            return ((CompassMeta) meta).hasLodestone();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "lodestone";
    }
}
