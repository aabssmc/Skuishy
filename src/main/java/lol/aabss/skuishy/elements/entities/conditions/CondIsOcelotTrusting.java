package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Ocelot;

@Name("Ocelot - Is Trusting")
@Description("True if the ocelot is trusting.")
@Examples({
        "if {_ocelot} is trusting:",
        "\tset trusting state of {_ocelot} to false"
})
@Since("2.8")
public class CondIsOcelotTrusting extends EntityCondition<Ocelot> {

    static {
        register(CondIsOcelotTrusting.class, "[ocelot] trusting", "entities");
    }

    @Override
    protected boolean run(Ocelot ocelot) {
        return ocelot.isTrusting();
    }

}
