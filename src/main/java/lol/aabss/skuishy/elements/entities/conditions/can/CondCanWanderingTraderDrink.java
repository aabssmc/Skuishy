package lol.aabss.skuishy.elements.entities.conditions.can;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.WanderingTrader;

@Name("Wandering Trader - Can Drink")
@Description("Returns true if the wandering trader can drink milk or potion.")
@Examples({
        "if {_sniffer} can drink milk:"
})
@Since("2.8")
public class CondCanWanderingTraderDrink extends EntityCondition<WanderingTrader> {

    static {
        register(CondCanWanderingTraderDrink.class, "[wandering[ ]trader] drink (:milk|[a ]potion)", "entities");
    }

    @Override
    protected boolean run(WanderingTrader wanderingTrader) {
        return tags.contains("milk") ? wanderingTrader.canDrinkMilk() : wanderingTrader.canDrinkPotion();
    }
}
