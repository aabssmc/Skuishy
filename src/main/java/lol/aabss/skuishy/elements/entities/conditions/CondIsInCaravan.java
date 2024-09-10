package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Llama;

@Name("Llama - In Caravan")
@Description("True if the llama is in a caravan.")
@Examples({
        "if {_llama} is in caravan:",
        "\tbroadcast \"very good!\""
})
@Since("2.8")
public class CondIsInCaravan extends EntityCondition<Llama> {

    static {
        register(CondIsInCaravan.class, "[llama] in caravan", "entities");
    }

    @Override
    protected boolean run(Llama llama) {
        return llama.inCaravan();
    }

}
