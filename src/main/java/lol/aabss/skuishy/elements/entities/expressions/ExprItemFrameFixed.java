package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ItemFrame;
import org.jetbrains.annotations.Nullable;

@Name("Item Frame - Fixed state")
@Description("Gets/sets the fixed state of an item frame.")
@Examples({
        "set itemframe fixed mode of {_itemframe} to false"
})
@Since("2.8")
public class ExprItemFrameFixed extends EntityExpression<ItemFrame, Boolean> {

    static {
        register(ExprItemFrameFixed.class, Boolean.class, "item[ |-]frame fixed [state|mode]", "entities");
    }

    @Override
    public Boolean get(ItemFrame itemFrame) {
        return itemFrame.isFixed();
    }

    @Override
    public void change(ItemFrame itemFrame, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            itemFrame.setFixed(aBoolean);
        }
    }
}

