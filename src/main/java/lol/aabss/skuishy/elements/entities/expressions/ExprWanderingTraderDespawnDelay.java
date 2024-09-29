package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.WanderingTrader;
import org.jetbrains.annotations.Nullable;

@Name("Wandering Trader - Despawn Delay")
@Description("Gets/sets the despawn delay of a wandering trader.")
@Examples({
        "set despawn delay of {_trader} to 20"
})
@Since("2.8")
public class ExprWanderingTraderDespawnDelay extends EntityExpression<WanderingTrader, Integer> {

    static {
        register(ExprWanderingTraderDespawnDelay.class, Integer.class, "[wandering[ ]trader] despawn delay", "entities");
    }

    @Override
    public Integer get(WanderingTrader wanderingTrader) {
        return wanderingTrader.getDespawnDelay();
    }

    @Override
    public void change(WanderingTrader wanderingTrader, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            wanderingTrader.setDespawnDelay(integer);
        }
    }
}
