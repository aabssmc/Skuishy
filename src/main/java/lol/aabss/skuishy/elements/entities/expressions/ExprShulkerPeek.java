package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Shulker;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Shulker - Peek State")
@Description({"Gets/sets the peek state of a shulker.", "Has to be between 0.0 and 1.0"})
@Examples({
        "set shulker peek of {_shulker} to 0.95"
})
@Since("2.8")
public class ExprShulkerPeek extends SimplePropertyExpression<Entity, Float> {

    static {
        register(ExprShulkerPeek.class, Float.class, "shulker peek [state]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "shulker peek";
    }

    @Override
    public @Nullable Float convert(Entity entity) {
        if (entity instanceof Shulker) {
            return ((Shulker) entity).getPeek();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Float> getReturnType() {
        return Float.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Float.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Float) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Shulker) {
                        float peek = (float) delta[0];
                        if (peek > 1.0) {
                            ((Shulker) entity).setPeek(1.0f);
                        } else if (peek < 0.0) {
                            ((Shulker) entity).setPeek(0.0f);
                        } else {
                            ((Shulker) entity).setPeek((Float) delta[0]);
                        }
                    }
                }
            }
        }
    }
}