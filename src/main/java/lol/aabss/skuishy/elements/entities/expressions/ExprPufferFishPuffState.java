package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.PufferFish;

@Name("Pufferfish - Puff State")
@Description("Gets/sets the puff state of a pufferfish (aka. how puffed up it is).")
@Examples({
        "set puff state of {_pufferfish} to 1"
})
@Since("2.8")
public class ExprPufferFishPuffState extends EntityExpression<PufferFish, Integer> {

    static {
        register(ExprPufferFishPuffState.class, Integer.class, "[pufferfish] puff (state|mode)", "entities");
    }

    @Override
    public Integer get(PufferFish pufferFish) {
        return pufferFish.getPuffState();
    }

    @Override
    public void change(PufferFish pufferFish, Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            pufferFish.setPuffState(integer);
        }
    }
}
