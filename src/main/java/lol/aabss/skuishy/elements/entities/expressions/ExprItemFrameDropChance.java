package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Item Frame - Drop Chance")
@Description({"Gets/sets the item frame drop chance", "Has to be between 0.0 and 1.0"})
@Examples({
        "set itemframe drop chance of {_itemframe} to 1.0 # will drop",
        "set itemframe drop chance of {_itemframe} to 0.0 # wont drop"
})
@Since("2.8")
public class ExprItemFrameDropChance extends SimplePropertyExpression<Entity, Float> {

    static {
        register(ExprItemFrameDropChance.class, Float.class, "item[ |-]frame [item] drop chance", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "itemframe drop chance";
    }

    @Override
    public @Nullable Float convert(Entity entity) {
        if (entity instanceof ItemFrame) {
            return ((ItemFrame) entity).getItemDropChance();
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
                    if (entity instanceof ItemFrame) {
                        float peek = (float) delta[0];
                        if (peek > 1.0) {
                            ((ItemFrame) entity).setItemDropChance(1.0f);
                        } else if (peek < 0.0) {
                            ((ItemFrame) entity).setItemDropChance(0.0f);
                        } else {
                            ((ItemFrame) entity).setItemDropChance((Float) delta[0]);
                        }
                    }
                }
            }
        }
    }
}