package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Panda;

@Name("Panda - Is Scared")
@Description("True if the panda is scared.")
@Examples({
        "if {_panda} is scared:",
        "\tset scared state of {_panda} to false"
})
@Since("2.8")
public class CondIsPandaScared extends EntityCondition<Panda> {

    static {
        register(CondIsPandaScared.class, "[panda] scared", "entities");
    }

    @Override
    protected boolean run(Panda panda) {
        return panda.isScared();
    }
}
