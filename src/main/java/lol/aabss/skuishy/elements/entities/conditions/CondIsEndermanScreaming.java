package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Enderman;

@Name("Enderman - Is Screaming")
@Description("True if the enderman is screaming.")
@Examples({
        "if {_enderman} is screaming:",
        "\tset screaming state of {_enderman} to false"
})
@Since("2.8")
public class CondIsEndermanScreaming extends EntityCondition<Enderman> {

    static {
        register(CondIsEndermanScreaming.class, "[enderman] screaming", "entities");
    }

    @Override
    protected boolean run(Enderman enderman) {
        return enderman.isScreaming();
    }
}
