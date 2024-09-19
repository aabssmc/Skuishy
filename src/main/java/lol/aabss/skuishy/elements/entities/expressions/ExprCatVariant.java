package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.KeyedToEnum;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Cat;
import org.jetbrains.annotations.Nullable;

@Name("Cat - Variant")
@Description("Gets/sets the variant of an cat.")
@Examples({
        "set cat variant of {_cat} to tabby"
})
@Since("2.8")
public class ExprCatVariant extends EntityExpression<Cat, KeyedToEnum.CatVariant> {

    static {
        register(ExprCatVariant.class, KeyedToEnum.CatVariant.class, "cat (variant|type)", "entities");
    }

    @Override
    public KeyedToEnum.CatVariant get(Cat cat) {
        return KeyedToEnum.CatVariant.fromBukkit(cat.getCatType());
    }

    @Override
    public void change(Cat cat, @Nullable KeyedToEnum.CatVariant type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            cat.setCatType(type.toBukkit());
        }
    }
}