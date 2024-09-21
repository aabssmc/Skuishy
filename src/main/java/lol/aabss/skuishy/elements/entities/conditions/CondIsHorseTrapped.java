package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.SkeletonHorse;

@Name("Horse - Is Trapped")
@Description("Returns true if the horse is trapped.")
@Examples({
        "if {_horse} is horse trapped",
        "\tset horse trapped state of {_horse} to false"
})
@Since("2.8")
public class CondIsHorseTrapped extends EntityCondition<SkeletonHorse> {

    static {
        register(CondIsHorseTrapped.class, "horse trapped", "livingentities");
    }

    @Override
    protected boolean run(SkeletonHorse horse) {
        return horse.isTrapped();
    }
}
