package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Minecart;

@Name("Minecart - Is Slow When Empty")
@Description("True if the minecart is slow when empty.")
@Examples({
        "if {_minecart} is not slow when empty:",
        "\tset slow when empty state of {_minecart} to true"
})
@Since("2.8")
public class CondIsSlowWhenEmpty extends EntityCondition<Minecart> {

    static {
        register(CondIsSlowWhenEmpty.class, "[minecart] slow when empty", "entities");
    }

    @Override
    protected boolean run(Minecart minecart) {
        return minecart.isSlowWhenEmpty();
    }

}