package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.AbstractHorse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Is Rearing")
@Description("Gets/sets the rearing state of a horse.")
@Examples({
        "set horse rearing of {_horse} to true"
})
@Since("2.8")
public class ExprHorseIsRearing extends EntityExpression<AbstractHorse, Boolean> {

    static {
        register(ExprHorseIsRearing.class, Boolean.class, "[horse] [is] rearing [state|mode]", "entities");
    }

    @Override
    public Boolean get(AbstractHorse horse) {
        return horse.isRearing();
    }

    @Override
    public void change(AbstractHorse horse, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            horse.setRearing(aBoolean);
        }
    }
}