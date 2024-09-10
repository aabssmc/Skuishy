package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Slow When Empty")
@Description("Gets/sets the slow when empty state of a minecart.")
@Examples({
        "set slow when empty of {_minecart} to false"
})
@Since("2.8")
public class ExprMinecartSlowWhenEmpty extends EntityExpression<Minecart, Boolean> {

    static {
        register(ExprMinecartSlowWhenEmpty.class, Boolean.class, "[minecart] slow when empty [mode|state]", "entities");
    }

    @Override
    public Boolean get(Minecart minecart) {
        return minecart.isSlowWhenEmpty();
    }

    @Override
    public void change(Minecart minecart, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            minecart.setSlowWhenEmpty(aBoolean);
        }
    }
}
