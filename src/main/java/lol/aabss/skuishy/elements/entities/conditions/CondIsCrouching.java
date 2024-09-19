package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Fox;

@Name("Fox - Is Crouching")
@Description("True if the fox is crouching.")
@Examples({
        "if {_fox} is crouching:",
        "\tset crouching state of {_fox} to false"
})
@Since("2.8")
public class CondIsCrouching extends EntityCondition<Fox> {

    static {
        register(CondIsCrouching.class, "[fox] crouching", "entities");
    }

    @Override
    protected boolean run(Fox fox) {
        return fox.isCrouching();
    }

}
