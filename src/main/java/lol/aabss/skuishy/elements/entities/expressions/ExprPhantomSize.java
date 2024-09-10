package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Phantom;
import org.jetbrains.annotations.Nullable;

@Name("Phantom - Size")
@Description("Gets/sets the size of a phantom.")
@Examples({
        "set phantom size of {_phantom} to 12"
})
@Since("2.8")
public class ExprPhantomSize extends EntityExpression<Phantom, Integer> {

    static {
        register(ExprPhantomSize.class, Integer.class, "phantom size", "entities");
    }

    @Override
    public Integer get(Phantom phantom) {
        return phantom.getSize();
    }

    @Override
    public void change(Phantom phantom, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            phantom.setSize(integer);
        }
    }
}