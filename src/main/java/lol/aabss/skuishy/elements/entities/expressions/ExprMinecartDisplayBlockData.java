package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Display Block Data")
@Description("Gets/sets the display block data of a minecart.")
@Examples({
        "set display block data of {_minecart} to {_data}"
})
@Since("2.8")
public class ExprMinecartDisplayBlockData extends EntityExpression<Minecart, BlockData> {

    static {
        register(ExprMinecartDisplayBlockData.class, BlockData.class, "[minecart] display block data", "entities");
    }

    @Override
    public BlockData get(Minecart minecart) {
        return minecart.getDisplayBlockData();
    }

    @Override
    public void change(Minecart minecart, @Nullable BlockData blockData, Changer.ChangeMode mode) {
        if (blockData != null && mode == Changer.ChangeMode.SET) {
            minecart.setDisplayBlockData(blockData);
        }
    }
}