package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Bee;

@Name("Bee - Is Rolling")
@Description("Returns true if the bee is rolling.")
@Examples({
        "if {_bee} is rolling:"
})
@Since("2.8")
public class CondBeeIsRolling extends EntityCondition<Bee> {

    static {
        register(CondBeeIsRolling.class, "[bee] rolling", "entities");
    }

    @Override
    protected boolean run(Bee bee) {
        return bee.isRolling();
    }
}
