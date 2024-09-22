package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LightningStrike;
import org.jetbrains.annotations.Nullable;

@Name("Lightning - Flash Count")
@Description("Gets/sets the flash count of a lightning.")
@Examples({
        "set flash count of {_lightning} to 10"
})
@Since("2.8")
public class ExprLightningFlashCount extends EntityExpression<LightningStrike, Integer> {

    static {
        register(ExprLightningFlashCount.class, Integer.class, "[lightning] flash count", "entities");
    }

    @Override
    public Integer get(LightningStrike lightning) {
        return lightning.getFlashCount();
    }

    @Override
    public void change(LightningStrike lightning, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            lightning.setFlashCount(integer);
        }
    }
}