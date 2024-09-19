package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Cat;

@Name("Cat - Is Head Up")
@Description("True if the cat is head up:")
@Examples({
        "if {_cat} is head up:",
        "\tset head up of {_cat} to false"
})
@Since("2.8")
public class CondIsCatHeadUp extends EntityCondition<Cat> {

    static {
        register(CondIsCatHeadUp.class, "[cat] head up", "entities");
    }

    @Override
    protected boolean run(Cat cat) {
        return cat.isHeadUp();
    }
}
