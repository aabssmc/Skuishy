package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.AbstractHorse;

@Name("Horse - Is Rearing")
@Description("Returns true if the horse is rearing.")
@Examples({
        "if {_horse} is rearing",
        "\tset rearing state of {_horse} to false"
})
@Since("2.8")
public class CondIsHorseRearing extends EntityCondition<AbstractHorse> {

    static {
        register(CondIsHorseRearing.class, "[horse] rearing", "livingentities");
    }

    @Override
    protected boolean run(AbstractHorse horse) {
        return horse.isRearing();
    }
}
