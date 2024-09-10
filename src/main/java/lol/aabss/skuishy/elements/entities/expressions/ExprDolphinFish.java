package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Dolphin;
import org.jetbrains.annotations.Nullable;

@Name("Dolphin - Has Fish")
@Description("Gets/sets the has fish state of a dolphin.")
@Examples({
        "set has fish state of {_dolphin} to true"
})
@Since("2.8")
public class ExprDolphinFish extends EntityExpression<Dolphin, Boolean> {

    static {
        register(ExprDolphinFish.class, Boolean.class, "[dolphin] [has] fish (state|mode)", "entities");
    }

    @Override
    public Boolean get(Dolphin dolphin) {
        return dolphin.hasFish();
    }

    @Override
    public void change(Dolphin dolphin, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            dolphin.setHasFish(aBoolean);
        }
    }

}