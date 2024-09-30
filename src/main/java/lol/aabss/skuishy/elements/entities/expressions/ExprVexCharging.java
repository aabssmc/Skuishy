package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Vex;

import javax.annotation.Nullable;

@Name("Vex - Charging")
@Description("Gets/sets the charging state of a vex.")
@Examples({
        "set charging state of {_vex} to true"
})
@Since("2.8")
public class ExprVexCharging extends EntityExpression<Vex, Boolean> {

    static {
        register(ExprVexCharging.class, Boolean.class, "[vex] charging [state|mode]", "entities");
    }

    @Override
    public Boolean get(Vex vex) {
        return vex.isCharging();
    }

    @Override
    public void change(Vex vex, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            vex.setCharging(aBoolean);
        }
    }
}
