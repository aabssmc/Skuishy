package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;

@Name("Enderman - Carried Block")
@Description("Gets/sets the carried block of an enderman.")
@Examples({
        "set carried block of {_enderman} to {_blockdata}"
})
@Since("2.8")
public class ExprEndermanCarriedBlock extends EntityExpression<Enderman, BlockData> {

    static {
        register(ExprEndermanCarriedBlock.class, BlockData.class, "[enderman] carried block", "entities");
    }

    @Override
    public BlockData get(Enderman enderDragon) {
        return enderDragon.getCarriedBlock();
    }

    @Override
    public void change(Enderman enderman, BlockData blockData, Changer.ChangeMode mode) {
        if (blockData != null && mode == Changer.ChangeMode.SET) {
            enderman.setCarriedBlock(blockData);
        }
    }
}
