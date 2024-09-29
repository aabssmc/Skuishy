package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Trident;
import org.jetbrains.annotations.Nullable;

@Name("Trident - Loyalty Level")
@Description("Gets/Sets the loyalty level of a trident.")
@Examples({
        "set loyalty level of {_trident} to 20"
})
@Since("2.8")
public class ExprTridentLoyaltyLevel extends EntityExpression<Trident, Integer> {

    static {
        register(ExprTridentLoyaltyLevel.class, Integer.class, "[trident] loyalty level", "entities");
    }

    @Override
    public Integer get(Trident trident) {
        return trident.getLoyaltyLevel();
    }

    @Override
    public void change(Trident trident, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            trident.setLoyaltyLevel(integer);
        }
    }
}
