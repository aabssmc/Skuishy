package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Ender Crystal - Beam Target")
@Description("Gets/sets the beam target of an ender crystal.")
@Examples({
        "set beam target of {_crystal} to player"
})
@Since("2.8")
public class ExprCrystalBeamTarget extends SimplePropertyExpression<Entity, Location> {

    static {
        register(ExprCrystalBeamTarget.class, Location.class, "[end[er] crystal] beam target", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "beam target";
    }

    @Override
    public @Nullable Location convert(Entity entity) {
        if (entity instanceof EnderCrystal) {
            return ((EnderCrystal) entity).getBeamTarget();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(Location.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (delta[0] instanceof Location) {
            for (Entity entity : getExpr().getArray(e)) {
                if (entity instanceof EnderCrystal) {
                    if (mode == Changer.ChangeMode.SET) {
                        ((EnderCrystal) entity).setBeamTarget((Location) delta[0]);
                    } else if (mode == Changer.ChangeMode.RESET) {
                        ((EnderCrystal) entity).setBeamTarget(null);
                    }
                }
            }
        }

    }
}