package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Fox - Variant")
@Description("Gets/sets the variant of an fox.")
@Examples({
        "set fox variant of {_fox} to salt pepper"
})
@Since("2.8")
public class ExprFoxVariant extends SimplePropertyExpression<Entity, Fox.Type> {

    static {
        register(ExprFoxVariant.class, Fox.Type.class, "fox (variant|type)", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "fox variant";
    }

    @Override
    public @Nullable Fox.Type convert(Entity entity) {
        if (entity instanceof Fox) {
            return ((Fox) entity).getFoxType();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Fox.Type> getReturnType() {
        return Fox.Type.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Fox.Type.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Fox.Type) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Fox) {
                        ((Fox) entity).setFoxType((Fox.Type) delta[0]);
                    }
                }
            }
        }
    }
}