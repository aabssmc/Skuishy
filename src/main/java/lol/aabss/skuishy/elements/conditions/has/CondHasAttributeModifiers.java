package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.NonNull;

@Name("Items - Has Attribute Modifiers")
@Description("Returns true if the item has attribute modifiers.")
@Examples({
        "if {_i} has attribute mods:"
})
@Since("2.0")
public class CondHasAttributeModifiers extends PropertyCondition<ItemStack> {

    static{
        register(CondHasAttributeModifiers.class, PropertyType.HAVE, "attribute mod[ifier]s", "itemstacks");
    }

    @Override
    public boolean check(ItemStack item) {
        return item.getItemMeta().hasAttributeModifiers();
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "attribute modifiers";
    }
}
