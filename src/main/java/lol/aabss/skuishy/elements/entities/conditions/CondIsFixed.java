package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.ItemFrame;

@Name("Item Frame - Is Fixed")
@Description("True if the item frame is fixed.")
@Examples({
        "if {_itemframe} is itemframe fixed:",
        "\tset itemframe fixed mode of {_itemframe} to false"
})
@Since("2.8")
public class CondIsFixed extends EntityCondition<ItemFrame> {

    static {
        register(CondIsFixed.class, "item[ |-]frame fixed", "entities");
    }

    @Override
    protected boolean run(ItemFrame itemFrame) {
        return itemFrame.isFixed();
    }

}
