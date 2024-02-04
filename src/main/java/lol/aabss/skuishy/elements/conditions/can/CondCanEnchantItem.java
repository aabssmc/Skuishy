package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("ItemStack - Can Be Enchanted")
@Description("Returns true if the item can enchanted by the specified enchant.")
@Examples({
        "if event-item can be enchanted with sharpness:"
})
@Since("2.0")
public class CondCanEnchantItem extends Condition {
    static{
        Skript.registerCondition(CondCanEnchantItem.class,
                "%itemstack% can be enchanted (with|by) %enchantment%",
                "%itemstack% can( not|n't) be enchanted (with|by) %enchantment%"
        );
    }

    private Expression<ItemStack> item;
    private Expression<Enchantment> enchant;
    private boolean can;


    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        item = (Expression<ItemStack>) exprs[0];
        enchant = (Expression<Enchantment>) exprs[1];
        can = matchedPattern == 0;
        return true;
    }

    @Override
    public boolean check(@NonNull Event e) {
        ItemStack i = item.getSingle(e);
        Enchantment en = enchant.getSingle(e);
        if (i != null && en != null){
            return can == en.canEnchantItem(i);
        }
        return false;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "can be enchanted";
    }
}
