package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Ghast;
import org.jetbrains.annotations.Nullable;

@Name("Ghast - Explosion Power")
@Description("Gets/sets the ghast explosion power.")
@Examples({
        "set ghast explosion power of {_ghast} to 10"
})
@Since("2.8")
public class ExprGhastExplosionPower extends EntityExpression<Ghast, Integer> {

    static {
        register(ExprGhastExplosionPower.class, Integer.class, "[ghast] explosion power", "entities");
    }

    @Override
    public Integer get(Ghast ghast) {
        return ghast.getExplosionPower();
    }

    @Override
    public void change(Ghast ghast, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            ghast.setExplosionPower(integer);
        }
    }
}