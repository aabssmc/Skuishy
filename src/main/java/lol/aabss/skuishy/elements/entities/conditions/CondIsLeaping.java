package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Fox;

@Name("Fox - Is Leaping")
@Description("True if the fox is leaping.")
@Examples({
        "if {_fox} is leaping:",
        "\tset leaping state of {_fox} to false"
})
@Since("2.8")
public class CondIsLeaping extends EntityCondition<Fox> {

    static {
        register(CondIsLeaping.class, "[fox] leaping", "entities");
    }

    @Override
    protected boolean run(Fox fox) {
        return fox.isLeaping();
    }

}
