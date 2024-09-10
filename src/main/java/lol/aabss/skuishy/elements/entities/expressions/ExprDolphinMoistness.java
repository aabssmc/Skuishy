package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Dolphin;
import org.jetbrains.annotations.Nullable;

@Name("Dolphin - Moistness")
@Description("Gets/sets the moistness of a dolphin.")
@Examples({
        "set moistness of {_dolphin} to 20 # 1 second"
})
@Since("2.8")
public class ExprDolphinMoistness extends EntityExpression<Dolphin, Integer> {

    static {
        register(ExprDolphinMoistness.class, Integer.class, "[dolphin] moistness", "entities");
    }

    @Override
    public Integer get(Dolphin dolphin) {
        return dolphin.getMoistness();
    }

    @Override
    public void change(Dolphin dolphin, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            dolphin.setMoistness(integer);
        }
    }

}