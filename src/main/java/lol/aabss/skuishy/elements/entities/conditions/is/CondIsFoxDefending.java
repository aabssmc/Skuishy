package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Fox;

@Name("Fox - Is Defending")
@Description("True if the fox is defending.")
@Examples({
        "if {_fox} is defending:",
        "\tset defending state of {_fox} to false"
})
@Since("2.8")
public class CondIsFoxDefending extends EntityCondition<Fox> {

    static {
        register(CondIsFoxDefending.class, "[fox] defending", "entities");
    }

    @Override
    protected boolean run(Fox fox) {
        return fox.isDefending();
    }
}
