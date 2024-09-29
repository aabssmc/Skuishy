package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Turtle;

@Name("Turtle - Is Digging")
@Description("True if the turtle is digging.")
@Examples({
        "if {_turtle} is digging:"
})
@Since("2.8")
public class CondIsTurtleDigging extends EntityCondition<Turtle> {

    static {
        register(CondIsTurtleDigging.class, "[turtle] digging", "entities");
    }

    @Override
    protected boolean run(Turtle turtle) {
        return turtle.isDigging();
    }
}
