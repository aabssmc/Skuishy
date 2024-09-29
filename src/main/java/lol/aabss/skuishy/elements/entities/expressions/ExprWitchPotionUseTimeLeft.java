package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Witch;
import org.jetbrains.annotations.Nullable;

@Name("Witch - Potion Use Time Left")
@Description("Gets/Sets the potion use time left of a witch.")
@Examples({
        "set potion use time left of last spawned witch to 10"
})
@Since("2.1")
public class ExprWitchPotionUseTimeLeft extends EntityExpression<Witch, Integer> {

    static {
        register(ExprWitchPotionUseTimeLeft.class, Integer.class, "[witch] potion use [time] [left]", "entities");
    }

    @Override
    public Integer get(Witch witch) {
        return witch.getPotionUseTimeLeft();
    }

    @Override
    public void change(Witch witch, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            witch.setPotionUseTimeLeft(integer);
        }
    }
}
