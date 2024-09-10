package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Fox;

@Name("Fox - Is Interested")
@Description("True if the fox is interested.")
@Examples({
        "if {_fox} is interested:",
        "\tset interested state of {_fox} to false"
})
@Since("2.8")
public class CondIsInterested extends EntityCondition<Fox> {

    static {
        register(CondIsInterested.class, "interested", "entities");
    }

    @Override
    protected boolean run(Fox fox) {
        return fox.isInterested();
    }

}
