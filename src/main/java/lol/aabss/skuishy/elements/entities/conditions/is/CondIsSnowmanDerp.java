package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Snowman;

@Name("Snowman - Is Derp")
@Description("True if the snowman is derp.")
@Examples({
        "if {_snowman} is derp:",
        "\tset derp state of {_snowman} to false"
})
@Since("2.8")
public class CondIsSnowmanDerp extends EntityCondition<Snowman> {

    static {
        register(CondIsSnowmanDerp.class, "[snowman] derp", "entities");
    }

    @Override
    protected boolean run(Snowman snowman) {
        return snowman.isDerp();
    }
}
