package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Ender Crystal - Showing Bottom")
@Description("Gets/sets the showing bottom state of an ender crystal.")
@Examples({
        "set showing bottom of {_crystal} to true"
})
@Since("2.8")
public class ExprCrystalShowingBottom extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprCrystalShowingBottom.class, Boolean.class, "[[end[er]] crystal] showing bottom [state|mode]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "beam target";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof EnderCrystal) {
            return ((EnderCrystal) entity).isShowingBottom();
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
        if (delta[0] instanceof Boolean) {
            for (Entity entity : getExpr().getArray(e)) {
                if (entity instanceof EnderCrystal) {
                    if (mode == Changer.ChangeMode.SET) {
                        ((EnderCrystal) entity).setShowingBottom((Boolean) delta[0]);
                    }
                }
            }
        }

    }
}
