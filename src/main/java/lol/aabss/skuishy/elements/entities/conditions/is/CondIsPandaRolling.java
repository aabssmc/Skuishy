package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Panda;

@Name("Panda - Is Rolling")
@Description("True if the panda is rolling.")
@Examples({
        "if {_panda} is rolling:",
        "\tset rolling state of {_panda} to false"
})
@Since("2.8")
public class CondIsPandaRolling extends EntityCondition<Panda> {

    static {
        register(CondIsPandaRolling.class, "[panda] rolling", "entities");
    }

    @Override
    protected boolean run(Panda panda) {
        return panda.isRolling();
    }
}
