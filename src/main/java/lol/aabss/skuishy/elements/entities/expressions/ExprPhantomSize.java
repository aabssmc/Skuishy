package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Phantom - Size")
@Description("Gets/sets the size of a phantom.")
@Examples({
        "set phantom size of {_phantom} to 12"
})
@Since("2.8")
public class ExprPhantomSize extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprPhantomSize.class, Integer.class, "phantom size", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "phantom size";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof Phantom) {
            return ((Phantom) entity).getSize();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Integer.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Integer) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Phantom) {
                        ((Phantom) entity).setSize((Integer) delta[0]);
                    }
                }
            }
        }
    }
}