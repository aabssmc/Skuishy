package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Ghast;
import org.jetbrains.annotations.Nullable;

@Name("Ghast - Charging")
@Description("Gets/sets the ghast charging state.")
@Examples({
        "set ghast charging state of {_ghast} to true"
})
@Since("2.8")
public class ExprGhastCharging extends EntityExpression<Ghast, Boolean> {

    static {
        register(ExprGhastCharging.class, Boolean.class, "[ghast] charging [mode|state]", "entities");
    }

    @Override
    public Boolean get(Ghast ghast) {
        return ghast.isCharging();
    }

    @Override
    public void change(Ghast ghast, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            ghast.setCharging(aBoolean);
        }
    }
}