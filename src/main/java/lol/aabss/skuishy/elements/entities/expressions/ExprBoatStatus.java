package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Boat - Status")
@Description("Gets the boat status.")
@Examples({
        "set boat status of {_boat} to in water"
})
@Since("2.8")
public class ExprBoatStatus extends SimplePropertyExpression<Entity, Boat.Status> {

    static {
        register(ExprBoatStatus.class, Boat.Status.class, "boat status", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "boat status";
    }

    @Override
    public @Nullable Boat.Status convert(Entity entity) {
        if (entity instanceof Boat) {
            return ((Boat) entity).getStatus();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Boat.Status> getReturnType() {
        return Boat.Status.class;
    }
}