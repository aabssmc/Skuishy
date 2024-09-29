package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.ChestedHorse;

@Name("Horse - Is Carrying Chest")
@Description("Returns true if the horse is carrying chest.")
@Examples({
        "if {_horse} is carrying chest",
        "\tset carrying chest state of {_horse} to false"
})
@Since("2.8")
public class CondIsHorseCarryingChest extends EntityCondition<ChestedHorse> {

    static {
        register(CondIsHorseCarryingChest.class, "[horse] carrying chest", "livingentities");
    }

    @Override
    protected boolean run(ChestedHorse horse) {
        return horse.isCarryingChest();
    }
}
