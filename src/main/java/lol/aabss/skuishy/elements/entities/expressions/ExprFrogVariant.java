package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.KeyedToEnum;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Frog;
import org.jetbrains.annotations.Nullable;

@Name("Frog - Variant")
@Description("Gets/sets the variant of an frog.")
@Examples({
        "set frog variant of {_frog} to cold"
})
@Since("2.8")
public class ExprFrogVariant extends EntityExpression<Frog, KeyedToEnum.FrogVariant> {

    static {
        register(ExprFrogVariant.class, KeyedToEnum.FrogVariant.class, "frog (variant|type)", "entities");
    }

    @Override
    public KeyedToEnum.FrogVariant get(Frog frog) {
        return KeyedToEnum.FrogVariant.fromBukkit(frog.getVariant());
    }

    @Override
    public void change(Frog frog, KeyedToEnum.@Nullable FrogVariant type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            frog.setVariant(type.toBukkit());
        }
    }
}