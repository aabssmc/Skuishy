package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Name("Item - Has Display Name")
@Description("Returns true if the item has a display name.")
@Examples({
        "if 1 of player's tool has a display name:"
})
@Since("2.0")
public class CondHasDisplayName extends PropertyCondition<ItemStack> {

    static{
        register(CondHasDisplayName.class, PropertyType.HAVE, "[a] display[ ]name", "itemstacks");
    }

    @Override
    public boolean check(ItemStack itemStack) {
        return itemStack.getItemMeta().hasDisplayName();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "display name";
    }
}
