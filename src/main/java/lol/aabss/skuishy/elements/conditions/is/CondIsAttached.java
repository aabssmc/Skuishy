package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.block.Block;
import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

@Name("Block - Is Attached")
@Description("Returns true if the block is attached.")
@Examples({
        "if target block is attached:"
})
@Since("2.0")
public class CondIsAttached extends PropertyCondition<Block> {

    static{
        register(CondIsAttached.class,
                PropertyType.BE,
                "attached",
                "blocks");
    }

    @Override
    public boolean check(Block block) {
        BlockData data = block.getBlockData();
        if (data instanceof Attachable){
            return ((Attachable) data).isAttached();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "attached";
    }
}
