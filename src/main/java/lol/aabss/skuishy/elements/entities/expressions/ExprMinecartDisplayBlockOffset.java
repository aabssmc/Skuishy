package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Display Block Offset")
@Description("Gets/sets the display block offset of a minecart.")
@Examples({
        "set display block offset of {_minecart} to 20"
})
@Since("2.8")
public class ExprMinecartDisplayBlockOffset extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprMinecartDisplayBlockOffset.class, Integer.class, "[minecart] display block offset", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "display block offset";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof Minecart) {
            return ((Minecart) entity).getDisplayBlockOffset();
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
                    if (entity instanceof Minecart) {
                        ((Minecart) entity).setDisplayBlockOffset((Integer) delta[0]);
                    }
                }
            }
        }
    }
}