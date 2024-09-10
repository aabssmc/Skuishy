package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Allay;

@Name("Allay - Is Dancing")
@Description("True if the allay is dancing.")
@Examples({
        "if {_allay} is dancing:",
        "\tset dash state of {_allay} to false"
})
@Since("2.8")
public class CondIsDancing extends EntityCondition<Allay> {

    static {
        register(CondIsDancing.class, "[allay] dancing", "entities");
    }

    @Override
    protected boolean run(Allay allay) {
        return allay.isDancing();
    }

}
