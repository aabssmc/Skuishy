package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Rotation;
import org.bukkit.entity.ItemFrame;
import org.jetbrains.annotations.Nullable;

@Name("Item Frame - Rotation")
@Description("Gets/sets the rotation of an item frame.")
@Examples({
        "set rotation of {_itemframe} to clockwise 135"
})
@Since("2.8")
public class ExprItemFrameRotation extends EntityExpression<ItemFrame, Rotation> {

    static {
        register(ExprItemFrameRotation.class, Rotation.class, "item[ ]frame rotation", "entities");
    }

    @Override
    public Rotation get(ItemFrame itemFrame) {
        return itemFrame.getRotation();
    }

    @Override
    public void change(ItemFrame itemFrame, @Nullable Rotation rotation, Changer.ChangeMode mode) {
        if (rotation != null && mode == Changer.ChangeMode.SET) {
            itemFrame.setRotation(rotation);
        }
    }
}
