package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Vex;

import javax.annotation.Nullable;

@Name("Vex - Limited Lifetime Ticks")
@Description("Gets/sets the number of limited lifetime ticks of a vex.")
@Examples({
        "set limited lifetime ticks of {_vex} to 200"
})
@Since("2.8")
public class ExprVexLimitedLifetimeTicks extends EntityExpression<Vex, Integer> {

    static {
        register(ExprVexLimitedLifetimeTicks.class, Integer.class, "[vex] limited lifetime ticks", "entities");
    }

    @Override
    public Integer get(Vex vex) {
        return vex.getLimitedLifetimeTicks();
    }

    @Override
    public void change(Vex vex, @Nullable Integer ticks, Changer.ChangeMode mode) {
        if (ticks != null && mode == Changer.ChangeMode.SET) {
            vex.setLimitedLifetimeTicks(ticks);
        }
    }
}
