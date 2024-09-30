package lol.aabss.skuishy.elements.entities.conditions.can;

import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Wither;

@Name("Wither - Can Travel Through Portals")
@Description("Returns true if the wither can travel through portals.")
@Examples({
        "if {_wither} can travel through portals:"
})
@Since("2.8")
public class CondCanWitherTravelThroughPortals extends EntityCondition<Wither> {

    static {
        register(CondCanWitherTravelThroughPortals.class, PropertyType.CAN, "[wither] travel through portals", "entities");
    }

    @Override
    protected boolean run(Wither wither) {
        return wither.canTravelThroughPortals();
    }
}
