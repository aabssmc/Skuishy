package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Tadpole;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Tadpole - Age Lock")
@Description("Gets/sets the age lock of the tadpole.")
@Examples({
        "set age lock of {_tadpole} to true"
})
@Since("2.8")
public class ExprTadpoleAge extends SimplePropertyExpression<Entity, Boolean> {

    static {
        if (Skript.classExists("org.bukkit.entity.Tadpole")) {
            register(ExprTadpoleAge.class, Boolean.class, "[tadpole] age lock", "entities");
        }
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "age lock";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof Tadpole) {
            return ((Tadpole) entity).getAgeLock();
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
                    if (entity instanceof Tadpole) {
                        ((Tadpole) entity).setAgeLock((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}
