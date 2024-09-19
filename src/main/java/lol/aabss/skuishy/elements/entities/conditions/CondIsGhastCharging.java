package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Ghast;

@Name("Ghast - Charging")
@Description("True if the ghast is charging.")
@Examples({
        "if {_ghast} is charging:",
        "\tset ghast charging of {_ghast} to false"
})
@Since("2.8")
public class CondIsGhastCharging extends EntityCondition<Ghast> {

    static {
        register(CondIsGhastCharging.class, "[ghast] charging", "entities");
    }

    @Override
    protected boolean run(Ghast ghast) {
        return ghast.isCharging();
    }

}
