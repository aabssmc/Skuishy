package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.WanderingTrader;
import org.jetbrains.annotations.Nullable;

@Name("Wandering Trader - Drink State")
@Description("Gets/sets the drink state of a wandering trader.")
@Examples({
        "set drink milk of {_trader} to true"
})
@Since("2.8")
public class ExprWanderingTraderDrink extends EntityExpression<WanderingTrader, Boolean> {

    static {
        register(ExprWanderingTraderDrink.class, Boolean.class, "[wandering[ ]trader] [can] drink (:milk|[a] potion) [state|mode]", "entities");
    }

    @Override
    public Boolean get(WanderingTrader wanderingTrader) {
        return tags.contains("milk") ? wanderingTrader.canDrinkMilk() : wanderingTrader.canDrinkPotion();
    }

    @Override
    public void change(WanderingTrader wanderingTrader, @Nullable Boolean bool, Changer.ChangeMode mode) {
        if (bool != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("milk")) {
                wanderingTrader.setCanDrinkMilk(bool);
            } else {
                wanderingTrader.setCanDrinkPotion(bool);
            }
        }
    }
}
