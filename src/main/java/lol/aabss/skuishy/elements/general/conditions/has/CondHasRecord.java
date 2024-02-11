package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.jetbrains.annotations.NotNull;

@Name("Jukebox - Has Record")
@Description("Returns true if the jukebox has a record.")
@Examples({
        "if {_b} has a record:"
})
@Since("2.0")
public class CondHasRecord extends PropertyCondition<Block> {

    static{
        if (Skript.methodExists(Jukebox.class, "hasRecord")) {
            register(CondHasRecord.class, PropertyType.HAVE, "[a] (record|[music] dis(s|k))", "blocks");
        }
    }

    @Override
    public boolean check(Block block) {
        if (block instanceof Jukebox){
            return ((Jukebox) block).hasRecord();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "record";
    }
}
