package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Endermite;

import javax.annotation.Nullable;

@Name("Endermite - Lifetime Ticks")
@Description("Gets/sets the number of lifetime ticks of a endermite.")
@Examples({
        "set lifetime ticks of {_endermite} to 200"
})
@Since("2.8")
public class ExprEndermiteLifetimeTicks extends EntityExpression<Endermite, Integer> {

    static {
        register(ExprEndermiteLifetimeTicks.class, Integer.class, "[endermite] lifetime ticks", "entities");
    }

    @Override
    public Integer get(Endermite endermite) {
        return endermite.getLifetimeTicks();
    }

    @Override
    public void change(Endermite endermite, @Nullable Integer ticks, Changer.ChangeMode mode) {
        if (ticks != null && mode == Changer.ChangeMode.SET) {
            endermite.setLifetimeTicks(ticks);
        }
    }
}
