package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Panda;

@Name("Panda - Is Eating")
@Description("True if the panda is eating.")
@Examples({
        "if {_panda} is eating:",
        "\tset eating state of {_panda} to false"
})
@Since("2.8")
public class CondIsPandaEating extends EntityCondition<Panda> {

    static {
        register(CondIsPandaEating.class, "[panda] eating", "entities");
    }

    @Override
    protected boolean run(Panda panda) {
        return panda.isEating();
    }
}
