package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ItemFrame;
import org.jetbrains.annotations.Nullable;

@Name("Item Frame - Drop Chance")
@Description({"Gets/sets the item frame drop chance", "Has to be between 0.0 and 1.0"})
@Examples({
        "set itemframe drop chance of {_itemframe} to 1.0 # will drop",
        "set itemframe drop chance of {_itemframe} to 0.0 # wont drop"
})
@Since("2.8")
public class ExprItemFrameDropChance extends EntityExpression<ItemFrame, Float> {

    static {
        register(ExprItemFrameDropChance.class, Float.class, "item[ |-]frame [item] drop chance", "entities");
    }

    @Override
    public Float get(ItemFrame itemFrame) {
        return itemFrame.getItemDropChance();
    }

    @Override
    public void change(ItemFrame itemFrame, @Nullable Float aFloat, Changer.ChangeMode mode) {
        if (aFloat != null && mode == Changer.ChangeMode.SET) {
            itemFrame.setItemDropChance(aFloat);
        }
    }
}