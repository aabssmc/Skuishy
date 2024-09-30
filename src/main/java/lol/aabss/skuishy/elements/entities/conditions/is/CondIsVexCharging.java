package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Vex;

@Name("Vex - Is Charging")
@Description("Returns true if the vex is charging.")
@Examples({
        "if last spawned vex is charging:"
})
@Since("2.8")
public class CondIsVexCharging extends EntityCondition<Vex> {

    static {
        register(CondIsVexCharging.class, "[vex] charging", "entities");
    }

    @Override
    protected boolean run(Vex vex) {
        return vex.isCharging();
    }
}
