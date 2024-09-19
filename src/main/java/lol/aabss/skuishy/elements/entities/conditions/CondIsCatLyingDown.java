package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Cat;

@Name("Cat - Is Lying Down")
@Description("True if the cat is lying down:")
@Examples({
        "if {_cat} is lying down:",
        "\tset lying down of {_cat} to false"
})
@Since("2.8")
public class CondIsCatLyingDown extends EntityCondition<Cat> {

    static {
        register(CondIsCatLyingDown.class, "[cat] lying down", "entities");
    }

    @Override
    protected boolean run(Cat cat) {
        return cat.isLyingDown();
    }
}
