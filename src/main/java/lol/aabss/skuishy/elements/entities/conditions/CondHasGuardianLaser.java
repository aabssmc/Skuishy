package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Guardian;

@Name("Guardian - Has Laser")
@Description("True if the guardian has laser")
@Examples({
        "if {_guardian} has a laser:",
        "\tset has laser state of {_guardian} to false"
})
@Since("2.8")
public class CondHasGuardianLaser extends EntityCondition<Guardian> {

    static {
        register(CondHasGuardianLaser.class, PropertyType.HAVE, "[guardian] [a[n]] laser", "entities");
    }

    @Override
    protected boolean run(Guardian guardian) {
        return guardian.hasLaser();
    }
}
