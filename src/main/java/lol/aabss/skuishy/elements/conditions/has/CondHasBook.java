package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Lectern;
import org.eclipse.jdt.annotation.NonNull;

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
    protected @NonNull String getPropertyName() {
        return "book";
    }
}
