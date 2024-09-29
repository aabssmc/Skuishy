package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.AbstractHorse;

@Name("Horse - Is Eating")
@Description("Returns true if the horse is eating.")
@Examples({
        "if {_horse} is horse eating",
        "\tset horse eating of {_horse} to false"
})
@Since("2.8")
public class CondIsHorseEating extends EntityCondition<AbstractHorse> {

    static {
        register(CondIsHorseEating.class, "horse eating [:grass]", "livingentities");
    }

    @Override
    protected boolean run(AbstractHorse horse) {
        return tags.contains("grass") ? horse.isEatingGrass() : horse.isEating();
    }
}
