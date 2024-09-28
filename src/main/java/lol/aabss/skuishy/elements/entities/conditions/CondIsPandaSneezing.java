package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Panda;

@Name("Panda - Is Sneezing")
@Description("True if the panda is sneezing.")
@Examples({
        "if {_panda} is sneezing:",
        "\tset sneezing state of {_panda} to false"
})
@Since("2.8")
public class CondIsPandaSneezing extends EntityCondition<Panda> {

    static {
        register(CondIsPandaSneezing.class, "[panda] sneezing", "entities");
    }

    @Override
    protected boolean run(Panda panda) {
        return panda.isSneezing();
    }
}
