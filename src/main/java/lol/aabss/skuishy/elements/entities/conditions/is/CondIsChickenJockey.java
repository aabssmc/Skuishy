package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Chicken;

@Name("Chicken - Is Chicken Jockey")
@Description("True if the chicken is a chicken jockey.")
@Examples({
        "if {_chicken} is chicken jockey:",
        "\tset chicken jockey state of {_chicken} to false"
})
@Since("2.8")
public class CondIsChickenJockey extends EntityCondition<Chicken> {

    static {
        register(CondIsChickenJockey.class, "[a] chicken jockey", "entities");
    }

    @Override
    protected boolean run(Chicken chicken) {
        return chicken.isChickenJockey();
    }

}
