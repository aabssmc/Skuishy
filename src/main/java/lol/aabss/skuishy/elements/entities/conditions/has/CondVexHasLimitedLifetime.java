package lol.aabss.skuishy.elements.entities.conditions.has;

import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Vex;

@Name("Vex - Has Limited Lifetime")
@Description("Returns true if the vex has a limited lifetime.")
@Examples({
        "if {_vex} has limited lifetime:"
})
@Since("2.8")
public class CondVexHasLimitedLifetime extends EntityCondition<Vex> {

    static {
        register(CondVexHasLimitedLifetime.class, PropertyType.HAVE, "[vex] limited lifetime", "entities");
    }

    @Override
    protected boolean run(Vex vex) {
        return vex.hasLimitedLifetime();
    }
}
