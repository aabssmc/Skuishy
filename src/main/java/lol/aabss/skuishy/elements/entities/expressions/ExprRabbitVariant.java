package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Rabbit;
import org.jetbrains.annotations.Nullable;

@Name("Rabbit - Variant")
@Description("Gets/sets the variant of an rabbit.")
@Examples({
        "set rabbit variant of {_rabbit} to salt pepper"
})
@Since("2.8")
public class ExprRabbitVariant extends EntityExpression<Rabbit, Rabbit.Type> {

    static {
        register(ExprRabbitVariant.class, Rabbit.Type.class, "rabbit (variant|type)", "entities");
    }

    @Override
    public Rabbit.Type get(Rabbit rabbit) {
        return rabbit.getRabbitType();
    }

    @Override
    public void change(Rabbit rabbit, Rabbit.@Nullable Type type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            rabbit.setRabbitType(type);
        }
    }
}