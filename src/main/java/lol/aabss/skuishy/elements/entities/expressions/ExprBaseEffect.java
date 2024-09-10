package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Arrow;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.Nullable;

@Name("Arrow - Base Potion Type")
@Description("Gets/Sets the base potion type of an arrow.")
@Examples({
        "set base potion type to strong harming"
})
@Since("2.1")
public class ExprBaseEffect extends EntityExpression<Arrow, PotionType> {

    static {
        if (Skript.methodExists(Arrow.class, "getBasePotionType")) {
            register(ExprBaseEffect.class, PotionType.class, "base (potion|effect) type", "entities");
        }
    }

    @Override
    public PotionType get(Arrow arrow) {
        return arrow.getBasePotionType();
    }

    @Override
    public void change(Arrow arrow, @Nullable PotionType potionType, Changer.ChangeMode mode) {
        if (potionType != null && mode == Changer.ChangeMode.SET) {
            arrow.setBasePotionType(potionType);
        }
    }
}
