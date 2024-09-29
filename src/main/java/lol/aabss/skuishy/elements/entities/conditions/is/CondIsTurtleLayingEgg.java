package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Turtle;

@Name("Turtle - Is Laying Egg")
@Description("True if the turtle is laying egg.")
@Examples({
        "if {_turtle} is laying egg:"
})
@Since("2.8")
public class CondIsTurtleLayingEgg extends EntityCondition<Turtle> {

    static {
        register(CondIsTurtleLayingEgg.class, "[turtle] laying egg", "entities");
    }

    @Override
    protected boolean run(Turtle turtle) {
        return turtle.isLayingEgg();
    }
}
