package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.KeyedToEnum;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Wolf;

import javax.annotation.Nullable;

@Name("Wolf - Variant")
@Description("Gets/sets the variant of a wolf.")
@Examples({
        "set variant of {_wolf} to cold"
})
@Since("2.8")
public class ExprWolfVariant extends EntityExpression<Wolf, KeyedToEnum.WolfVariant> {

    static {
        register(ExprWolfVariant.class, KeyedToEnum.WolfVariant.class, "wolf variant", "entities");
    }

    @Override
    public KeyedToEnum.WolfVariant get(Wolf wolf) {
        return KeyedToEnum.WolfVariant.fromBukkit(wolf.getVariant());
    }

    @Override
    public void change(Wolf wolf, @Nullable KeyedToEnum.WolfVariant variant, Changer.ChangeMode mode) {
        if (variant != null && mode == Changer.ChangeMode.SET) {
            wolf.setVariant(variant.toBukkit());
        }
    }
}
