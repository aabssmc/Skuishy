package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Allay - Duplication Cooldown")
@Description("Gets/sets the duplication cooldown of an allay in ticks.")
@Examples({
        "set duplication cooldown of {_allay} to 20 # 1 second"
})
@Since("2.8")
public class ExprAllayDuplicationCooldown extends SimplePropertyExpression<Entity, Long> {

    static {
        register(ExprAllayDuplicationCooldown.class, Long.class, "[allay] duplication cooldown [ticks]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "duplication cooldown ticks";
    }

    @Override
    public @Nullable Long convert(Entity entity) {
        if (entity instanceof Allay) {
            return ((Allay) entity).getDuplicationCooldown();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(Long.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        for (Entity entity : getExpr().getArray(e)) {
            if (entity instanceof Allay) {
                if (mode == Changer.ChangeMode.SET) {
                    if (delta[0] instanceof Long) {
                        ((Allay) entity).setDuplicationCooldown((Long) delta[0]);
                    }
                } else if (mode == Changer.ChangeMode.RESET) {
                    ((Allay) entity).resetDuplicationCooldown();
                }
            }
        }
    }
}
