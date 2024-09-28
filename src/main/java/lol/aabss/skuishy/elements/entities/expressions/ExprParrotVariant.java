package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Parrot;
import org.jetbrains.annotations.Nullable;

@Name("Parrot - Variant")
@Description("Gets/sets the variant of an parrot.")
@Examples({
        "set parrot variant of {_parrot} to red"
})
@Since("2.8")
public class ExprParrotVariant extends EntityExpression<Parrot, Parrot.Variant> {

    static {
        register(ExprParrotVariant.class, Parrot.Variant.class, "parrot (variant|type)", "entities");
    }

    @Override
    public Parrot.Variant get(Parrot parrot) {
        return parrot.getVariant();
    }

    @Override
    public void change(Parrot parrot, @Nullable Parrot.Variant type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            parrot.setVariant(type);
        }
    }
}