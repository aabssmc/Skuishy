package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Camel;

@Name("Camel - Is Dashing")
@Description("True if the camel is dashing.")
@Examples({
        "if {_camel} is dashing:",
        "\tset dash state of {_camel} to false"
})
@Since("2.8")
public class CondIsDashing extends EntityCondition<Camel> {

    static {
        register(CondIsDashing.class, "[camel] dashing", "entities");
    }

    @Override
    protected boolean run(Camel camel) {
        return camel.isDashing();
    }
}
