package lol.aabss.skuishy.elements.entities.conditions.has;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Llama;

@Name("Llama - Has Caravan Tail")
@Description("True if the llama has a caravan tail.")
@Examples({
        "if {_llama} has caravan tail:",
        "\tbroadcast \"very good!\""
})
@Since("2.8")
public class CondHasLlamaCaravanTail extends EntityCondition<Llama> {

    static {
        register(CondHasLlamaCaravanTail.class, PropertyType.HAVE, "[llama] caravan tail", "entities");
    }

    @Override
    protected boolean run(Llama llama) {
        return llama.hasCaravanTail();
    }
}
