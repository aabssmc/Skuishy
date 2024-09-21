package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.SkeletonHorse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Is Trapped")
@Description("Gets/sets the trapped state of a horse.")
@Examples({
        "set horse trapped of {_horse} to true"
})
@Since("2.8")
public class ExprHorseIsTrapped extends EntityExpression<SkeletonHorse, Boolean> {

    static {
        register(ExprHorseIsTrapped.class, Boolean.class, "horse [is] trapped [state|mode]", "entities");
    }

    @Override
    public Boolean get(SkeletonHorse horse) {
        return horse.isTrapped();
    }

    @Override
    public void change(SkeletonHorse horse, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            horse.setTrapped(aBoolean);
        }
    }
}