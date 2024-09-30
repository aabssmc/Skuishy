package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Wither;

import javax.annotation.Nullable;

@Name("Wither - Invulnerable Ticks")
@Description("Gets/sets the number of invulnerable ticks of a wither.")
@Examples({
        "set wither invulnerable ticks of {_wither} to 200"
})
@Since("2.8")
public class ExprWitherInvulnerableTicks extends EntityExpression<Wither, Integer> {

    static {
        register(ExprWitherInvulnerableTicks.class, Integer.class, "wither invulnerable ticks", "entities");
    }

    @Override
    public Integer get(Wither wither) {
        return wither.getInvulnerableTicks();
    }

    @Override
    public void change(Wither wither, @Nullable Integer ticks, Changer.ChangeMode mode) {
        if (ticks != null && mode == Changer.ChangeMode.SET) {
            wither.setInvulnerableTicks(ticks);
        }
    }
}
