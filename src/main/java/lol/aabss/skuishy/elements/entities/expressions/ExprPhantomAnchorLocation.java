package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Phantom - Anchor Location")
@Description("Gets/sets the anchor location of a phantom.")
@Examples({
        "set phantom anchor location of {_phantom} to player's location"
})
@Since("2.8")
public class ExprPhantomAnchorLocation extends SimplePropertyExpression<Entity, Location> {

    static {
        register(ExprPhantomAnchorLocation.class, Location.class, "[phantom] anchor location", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "anchor location";
    }

    @Override
    public @Nullable Location convert(Entity entity) {
        if (entity instanceof Phantom) {
            return ((Phantom) entity).getAnchorLocation();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Location.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Location) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Phantom) {
                        ((Phantom) entity).setAnchorLocation((Location) delta[0]);
                    }
                }
            }
        }
    }
}