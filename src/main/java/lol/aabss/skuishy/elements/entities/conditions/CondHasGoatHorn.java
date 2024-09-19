package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Goat;

@Name("Goat - Has Horn")
@Description("True if the goat has the horn:")
@Examples({
        "if {_goat} has left horn:",
        "\tset left horn of {_goat} to false"
})
@Since("2.8")
public class CondHasGoatHorn extends EntityCondition<Goat> {

    static {
        register(CondHasGoatHorn.class, PropertyType.HAVE, "[goat] (:left|right) horn", "entities");
    }

    @Override
    protected boolean run(Goat goat) {
        return tags.contains("left") ? goat.hasLeftHorn() : goat.hasRightHorn();
    }
}
