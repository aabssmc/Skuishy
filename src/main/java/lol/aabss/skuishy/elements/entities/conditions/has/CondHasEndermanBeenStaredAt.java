package lol.aabss.skuishy.elements.entities.conditions.has;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Enderman;

@Name("Enderman - Has Been Stared At")
@Description("True if the enderman has been stared at.")
@Examples({
        "if {_enderman} has been stared at:",
        "\tset stared at state state of {_enderman} to false"
})
@Since("2.8")
public class CondHasEndermanBeenStaredAt extends EntityCondition<Enderman> {

    static {
        register(CondHasEndermanBeenStaredAt.class, PropertyType.HAVE, "[enderman] stared at", "entities");
    }

    @Override
    protected boolean run(Enderman enderman) {
        return enderman.hasBeenStaredAt();
    }
}

