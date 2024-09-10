package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.TNTPrimed;
import org.jetbrains.annotations.Nullable;

@Name("Primed TNT - Block Data")
@Description("Gets/sets the block data of a primed tnt.")
@Examples({
        "set tnt block data of {_tnt} to {_blockdata}"
})
@Since("2.8")
public class ExprTntBlockData extends EntityExpression<TNTPrimed, BlockData> {

    static {
        register(ExprTntBlockData.class, BlockData.class, "[primed[-| ]]tnt block[ |-]data", "entities");
    }

    @Override
    public BlockData get(TNTPrimed tntPrimed) {
        return tntPrimed.getBlockData();
    }

    @Override
    public void change(TNTPrimed tntPrimed, @Nullable BlockData blockData, Changer.ChangeMode mode) {
        if (blockData != null && mode == Changer.ChangeMode.SET) {
            tntPrimed.setBlockData(blockData);
        }
    }
}
