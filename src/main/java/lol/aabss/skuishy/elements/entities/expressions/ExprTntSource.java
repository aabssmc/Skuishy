package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Primed TNT - Source")
@Description("Gets/sets the source of a primed tnt.")
@Examples({
        "set tnt source of {_tnt} to player"
})
@Since("2.8")
public class ExprTntSource extends SimplePropertyExpression<Entity, Entity> {

    static {
        register(ExprTntSource.class, Entity.class, "[primed[-| ]]tnt source", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "tnt source";
    }

    @Override
    public @Nullable Entity convert(Entity entity) {
        if (entity instanceof TNTPrimed) {
            return ((TNTPrimed) entity).getSource();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Entity> getReturnType() {
        return Entity.class;
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
            if (delta[0] instanceof Entity) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof TNTPrimed) {
                        ((TNTPrimed) entity).setSource((Entity) delta[0]);
                    }
                }
            }
        }
    }
}
