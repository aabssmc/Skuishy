package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.MushroomCow;
import org.jetbrains.annotations.Nullable;

@Name("Mushroom Cow - Variant")
@Description("Gets/sets the variant of an mushroom cow.")
@Examples({
        "set mushroom cow variant of {_mushroomcow} to red"
})
@Since("2.8")
public class ExprMushroomCowVariant extends EntityExpression<MushroomCow, MushroomCow.Variant> {

    static {
        register(ExprMushroomCowVariant.class, MushroomCow.Variant.class, "mushroom[ ]cow (variant|type)", "entities");
    }


    @Override
    public MushroomCow.Variant get(MushroomCow mushroomCow) {
        return mushroomCow.getVariant();
    }

    @Override
    public void change(MushroomCow mushroomCow, MushroomCow.@Nullable Variant type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            mushroomCow.setVariant(type);
        }
    }
}