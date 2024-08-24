package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Display Block Data")
@Description("Gets/sets the display block data of a minecart.")
@Examples({
        "set display block data of {_minecart} to {_data}"
})
@Since("2.8")
public class ExprMinecartDisplayBlockData extends SimplePropertyExpression<Entity, BlockData> {

    static {
        register(ExprMinecartDisplayBlockData.class, BlockData.class, "[minecart] display block data", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "display block data";
    }

    @Override
    public @Nullable BlockData convert(Entity entity) {
        if (entity instanceof Minecart) {
            return ((Minecart) entity).getDisplayBlockData();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends BlockData> getReturnType() {
        return BlockData.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(BlockData.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof BlockData) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Minecart) {
                        ((Minecart) entity).setDisplayBlockData((BlockData) delta[0]);
                    }
                }
            }
        }
    }
}