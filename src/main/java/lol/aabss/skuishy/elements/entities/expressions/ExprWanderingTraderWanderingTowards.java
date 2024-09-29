package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.WanderingTrader;
import org.jetbrains.annotations.Nullable;

@Name("Wandering Trader - Wandering Towards")
@Description("Gets/sets the wandering towards of a wandering trader.")
@Examples({
        "set wandering towards of {_trader} to player's location"
})
@Since("2.8")
public class ExprWanderingTraderWanderingTowards extends EntityExpression<WanderingTrader, Location> {

    static {
        register(ExprWanderingTraderWanderingTowards.class, Location.class, "[wandering[ ]trader] wandering towards", "entities");
    }

    @Override
    public Location get(WanderingTrader wanderingTrader) {
        return wanderingTrader.getWanderingTowards();
    }

    @Override
    public void change(WanderingTrader wanderingTrader, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            wanderingTrader.setWanderingTowards(location);
        }
    }
}
