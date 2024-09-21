package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ChestedHorse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Is Carrying Chest")
@Description("Gets/sets the is carrying chest state of a horse.")
@Examples({
        "set carrying chest of {_horse} to true"
})
@Since("2.8")
public class ExprHorseIsCarryingChest extends EntityExpression<ChestedHorse, Boolean> {

    static {
        register(ExprHorseIsCarryingChest.class, Boolean.class, "[horse] [is] carrying chest [state|mode]", "entities");
    }

    @Override
    public Boolean get(ChestedHorse horse) {
        return horse.isCarryingChest();
    }

    @Override
    public void change(ChestedHorse horse, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            horse.setCarryingChest(aBoolean);
        }
    }
}