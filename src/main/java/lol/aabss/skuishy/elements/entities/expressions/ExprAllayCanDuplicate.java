package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Allay - Can Duplicate")
@Description("Gets/sets the can duplicate state of an allay.")
@Examples({
        "set can duplicate state of {_allay} to true"
})
@Since("2.8")
public class ExprAllayCanDuplicate extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprAllayCanDuplicate.class, Boolean.class, "[allay] can duplicate [state|mode]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "can duplicate";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof Allay) {
            return ((Allay) entity).canDuplicate();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Boolean) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Allay) {
                        ((Allay) entity).setCanDuplicate((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}