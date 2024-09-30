package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Frog;

@Name("Frog - Variant")
@Description("Gets/sets the variant of an frog.")
@Examples({
        "set frog variant of {_frog} to cold"
})
@Since("2.8")
public class ExprFrogVariant extends EntityExpression<Frog, Frog.Variant> {

    static {
        register(ExprFrogVariant.class, Frog.Variant.class, "frog (variant|type)", "entities");
    }

    @Override
    public Frog.Variant get(Frog frog) {
        return frog.getVariant();
    }

    @Override
    public void change(Frog frog, Frog.Variant type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            frog.setVariant(type);
        }
    }
}