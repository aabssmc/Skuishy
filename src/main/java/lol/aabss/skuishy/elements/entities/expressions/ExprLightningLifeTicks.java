package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LightningStrike;
import org.jetbrains.annotations.Nullable;

@Name("Lightning - Life Ticks")
@Description("Gets/sets the life ticks of a lightning.")
@Examples({
        "set life ticks of {_lightning} to 10"
})
@Since("2.8")
public class ExprLightningLifeTicks extends EntityExpression<LightningStrike, Integer> {

    static {
        register(ExprLightningLifeTicks.class, Integer.class, "[lightning] life ticks", "entities");
    }

    @Override
    public Integer get(LightningStrike lightning) {
        return lightning.getLifeTicks();
    }

    @Override
    public void change(LightningStrike lightning, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            lightning.setLifeTicks(integer);
        }
    }
}