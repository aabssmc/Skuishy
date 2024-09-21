package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Guardian;

@Name("Guardian - Is Moving")
@Description("True if the guardian is moving.")
@Examples({
        "if {_guardian} is moving:",
        "\tbroadcast \"oh no!\""
})
@Since("2.8")
public class CondIsGuardianMoving extends EntityCondition<Guardian> {

    static {
        register(CondIsGuardianMoving.class, "[guardian] moving", "entities");
    }

    @Override
    protected boolean run(Guardian guardian) {
        return guardian.isMoving();
    }
}
