package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Fox;

@Name("Fox - Is Faceplanted")
@Description("True if the fox is faceplanted.")
@Examples({
        "if {_fox} is faceplanted:",
        "\tset factplanted state of {_fox} to false"
})
@Since("2.8")
public class CondIsFaceplanted extends EntityCondition<Fox> {

    static {
        register(CondIsFaceplanted.class, "[fox] faceplanted", "entities");
    }

    @Override
    protected boolean run(Fox fox) {
        return fox.isFaceplanted();
    }

}
