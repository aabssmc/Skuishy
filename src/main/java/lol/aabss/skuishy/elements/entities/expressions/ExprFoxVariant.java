package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Fox;
import org.jetbrains.annotations.Nullable;

@Name("Fox - Variant")
@Description("Gets/sets the variant of an fox.")
@Examples({
        "set fox variant of {_fox} to salt pepper"
})
@Since("2.8")
public class ExprFoxVariant extends EntityExpression<Fox, Fox.Type> {

    static {
        register(ExprFoxVariant.class, Fox.Type.class, "fox (variant|type)", "entities");
    }


    @Override
    public Fox.Type get(Fox fox) {
        return fox.getFoxType();
    }

    @Override
    public void change(Fox fox, Fox.@Nullable Type type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            fox.setFoxType(type);
        }
    }
}