package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Boat;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Boat - Variant")
@Description("Gets/sets the variant of an boat.")
@Examples({
        "set boat variant of {_boat} to oak"
})
@Since("2.8")
public class ExprBoatVariant extends SimplePropertyExpression<Entity, Boat.Type> {

    static {
        register(ExprBoatVariant.class, Boat.Type.class, "boat (variant|type)", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "boat variant";
    }

    @Override
    public @Nullable Boat.Type convert(Entity entity) {
        if (entity instanceof Boat) {
            return ((Boat) entity).getBoatType();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Boat.Type> getReturnType() {
        return Boat.Type.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boat.Type.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Boat.Type) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Boat) {
                        ((Boat) entity).setBoatType((Boat.Type) delta[0]);
                    }
                }
            }
        }
    }
}