package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Lectern;
import org.jetbrains.annotations.NotNull;

@Name("Lectern - Has Book")
@Description("Returns true if the lectern has a book.")
@Examples({
        "if target block has a book:"
})
@Since("2.0")
public class CondHasBook extends PropertyCondition<Block> {

    static{
        register(CondHasBook.class, PropertyType.HAVE, "[a] book", "blocks");
    }

    @Override
    public boolean check(Block block) {
        BlockData data = block.getBlockData();
        if (data instanceof Lectern){
            return ((Lectern) data).hasBook();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "book";
    }
}
