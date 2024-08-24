package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Camel;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Camel - Dashing State")
@Description("Gets/sets the dashing state of a camel.")
@Examples({
        "set dashing state of {_camel} to true"
})
@Since("2.8")
public class ExprCamelDash extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprCamelDash.class, Boolean.class, "[camel] dash[ing] [state|mode]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "dashing";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof Camel) {
            return ((Camel) entity).isDashing();
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
                    if (entity instanceof Camel) {
                        ((Camel) entity).setDashing((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}