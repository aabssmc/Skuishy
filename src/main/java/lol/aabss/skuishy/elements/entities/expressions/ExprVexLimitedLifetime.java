package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Vex;

import javax.annotation.Nullable;

@Name("Vex - Limited Lifetime")
@Description("Gets/sets whether a vex has a limited lifetime.")
@Examples({
        "set limited lifetime of {_vex} to true"
})
@Since("2.8")
public class ExprVexLimitedLifetime extends EntityExpression<Vex, Boolean> {

    static {
        register(ExprVexLimitedLifetime.class, Boolean.class, "[vex] limited lifetime [state|mode]", "entities");
    }

    @Override
    public Boolean get(Vex vex) {
        return vex.hasLimitedLifetime();
    }

    @Override
    public void change(Vex vex, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            vex.setLimitedLifetime(aBoolean);
        }
    }
}
