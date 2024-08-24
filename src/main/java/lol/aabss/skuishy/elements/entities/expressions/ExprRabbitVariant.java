package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Rabbit - Variant")
@Description("Gets/sets the variant of an rabbit.")
@Examples({
        "set rabbit variant of {_rabbit} to salt pepper"
})
@Since("2.8")
public class ExprRabbitVariant extends SimplePropertyExpression<Entity, Rabbit.Type> {

    static {
        register(ExprRabbitVariant.class, Rabbit.Type.class, "rabbit (variant|type)", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "rabbit variant";
    }

    @Override
    public @Nullable Rabbit.Type convert(Entity entity) {
        if (entity instanceof Rabbit) {
            return ((Rabbit) entity).getRabbitType();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Rabbit.Type> getReturnType() {
        return Rabbit.Type.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Rabbit.Type.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Rabbit.Type) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Rabbit) {
                        ((Rabbit) entity).setRabbitType((Rabbit.Type) delta[0]);
                    }
                }
            }
        }
    }
}