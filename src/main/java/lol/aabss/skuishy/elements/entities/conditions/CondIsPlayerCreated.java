package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.IronGolem;

@Name("Iron Golem - Is Player Created")
@Description("True if the iron golem is player created.")
@Examples({
        "if {_golem} is player created:",
        "\tset player created state of {_golem} to false"
})
@Since("2.8")
public class CondIsPlayerCreated extends EntityCondition<IronGolem> {

    static {
        register(CondIsPlayerCreated.class, "[[iron] golem] player created", "entities");
    }

    @Override
    protected boolean run(IronGolem ironGolem) {
        return ironGolem.isPlayerCreated();
    }

}