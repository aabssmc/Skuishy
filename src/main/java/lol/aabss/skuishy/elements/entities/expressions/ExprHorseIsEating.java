package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.AbstractHorse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Is Eating")
@Description("Gets/sets the eating state of a horse.")
@Examples({
        "set horse eating grass of {_horse} to true"
})
@Since("2.8")
public class ExprHorseIsEating extends EntityExpression<AbstractHorse, Boolean> {

    static {
        register(ExprHorseIsEating.class, Boolean.class, "horse [is] eating [:grass] [state|mode]", "entities");
    }

    @Override
    public Boolean get(AbstractHorse horse) {
        return tags.contains("grass") ? horse.isEatingGrass() : horse.isEating();
    }

    @Override
    public void change(AbstractHorse horse, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("grass")) {
                horse.setEatingGrass(aBoolean);
            } else {
                horse.setEating(aBoolean);
            }
        }
    }
}