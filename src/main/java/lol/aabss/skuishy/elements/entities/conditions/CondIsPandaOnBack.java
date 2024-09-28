package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Panda;

@Name("Panda - Is On Back")
@Description("True if the panda is on back.")
@Examples({
        "if {_panda} is on back:",
        "\tset on back state of {_panda} to false"
})
@Since("2.8")
public class CondIsPandaOnBack extends EntityCondition<Panda> {

    static {
        register(CondIsPandaOnBack.class, "[panda] on back", "entities");
    }

    @Override
    protected boolean run(Panda panda) {
        return panda.isOnBack();
    }
}
