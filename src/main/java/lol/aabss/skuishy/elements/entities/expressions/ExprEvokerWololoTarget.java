package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Sheep;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Evoker - Wololo Target")
@Description("Gets/sets the wololo target.")
@Examples({
        "set wololo target of {_evoker} to {_sheep}"
})
@Since("2.8")
public class ExprEvokerWololoTarget extends SimplePropertyExpression<Entity, Entity> {

    static {
        register(ExprEvokerWololoTarget.class, Entity.class, "[evoker] wololo target", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "wololo target";
    }

    @Override
    public @Nullable Entity convert(Entity entity) {
        if (entity instanceof Evoker) {
            return ((Evoker) entity).getWololoTarget();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Evoker> getReturnType() {
        return Evoker.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Entity.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Sheep) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Evoker) {
                        ((Evoker) entity).setWololoTarget((Sheep) delta[0]);
                    }
                }
            }
        }
    }
}