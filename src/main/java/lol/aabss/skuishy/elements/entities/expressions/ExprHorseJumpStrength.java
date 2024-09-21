package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.AbstractHorse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Jump Strength")
@Description("Gets/sets the jump strength of a horse.")
@Examples({
        "set horse jump strength of {_horse} to true"
})
@Since("2.8")
public class ExprHorseJumpStrength extends EntityExpression<AbstractHorse, Double> {

    static {
        register(ExprHorseJumpStrength.class, Double.class, "horse jump strength", "entities");
    }

    @Override
    public Double get(AbstractHorse horse) {
        return horse.getJumpStrength();
    }

    @Override
    public void change(AbstractHorse horse, @Nullable Double aDouble, Changer.ChangeMode mode) {
        if (aDouble != null && mode == Changer.ChangeMode.SET) {
            horse.setJumpStrength(aDouble);
        }
    }
}