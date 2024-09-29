package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Turtle;

@Name("Turtle - Is Going Home")
@Description("True if the turtle is going home.")
@Examples({
        "if {_turtle} is going home:"
})
@Since("2.8")
public class CondIsTurtleGoingHome extends EntityCondition<Turtle> {

    static {
        register(CondIsTurtleGoingHome.class, "[turtle] going home", "entities");
    }

    @Override
    protected boolean run(Turtle turtle) {
        return turtle.isGoingHome();
    }
}
