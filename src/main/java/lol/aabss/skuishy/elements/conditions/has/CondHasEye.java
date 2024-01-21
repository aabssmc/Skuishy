package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.EndPortalFrame;
import org.jetbrains.annotations.NotNull;

@Name("Block - Has Eye")
@Description("Returns true if the end portal frame has a eye of ender.")
@Examples({
        "if {_b} has ender eye:"
})
@Since("2.0")
public class CondHasEye extends PropertyCondition<Block> {

    static{
        register(CondHasEye.class, PropertyType.HAVE, "([ender] eye|eye of end[er])", "blocks");
    }

    @Override
    public boolean check(Block block) {
        BlockData data = block.getBlockData();
        if (data instanceof EndPortalFrame){
            return ((EndPortalFrame) data).hasEye();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "eye of ender";
    }
}
