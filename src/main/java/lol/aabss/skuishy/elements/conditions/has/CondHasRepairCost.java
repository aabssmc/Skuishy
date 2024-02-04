package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.eclipse.jdt.annotation.NonNull;

@Name("ItemStack - Has Repair Cost")
@Description("Returns true if the item has repair cost.")
@Examples({
        "if {_i} has repair cost:"
})
@Since("2.0")
public class CondHasRepairCost extends PropertyCondition<ItemStack> {

    static {
        register(CondHasRepairCost.class, PropertyType.HAVE, "repair cost", "itemstacks");
    }

    @Override
    public boolean check(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta instanceof Repairable){
            return ((Repairable) meta).hasRepairCost();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "repair cost";
    }
}
