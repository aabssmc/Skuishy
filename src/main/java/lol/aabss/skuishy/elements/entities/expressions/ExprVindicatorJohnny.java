package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Vindicator;
import org.jetbrains.annotations.Nullable;

@Name("Vindicator - Johnny State")
@Description("Gets/sets the johnny state of a vindicator.")
@Examples({
        "set johnny state of {_vindicator} to true"
})
@Since("2.8")
public class ExprVindicatorJohnny extends EntityExpression<Vindicator, Boolean> {

    static {
        register(ExprVindicatorJohnny.class, Boolean.class, "[vindicator] johnny [state|mode]", "entities");
    }

    @Override
    public Boolean get(Vindicator vindicator) {
        return vindicator.isJohnny();
    }

    @Override
    public void change(Vindicator vindicator, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            vindicator.setJohnny(aBoolean);
        }
    }
}
