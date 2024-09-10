package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Boat;

@Name("Boat - Status")
@Description("Gets the boat status.")
@Examples({
        "set boat status of {_boat} to in water"
})
@Since("2.8")
public class ExprBoatStatus extends EntityExpression<Boat, Boat.Status> {

    static {
        register(ExprBoatStatus.class, Boat.Status.class, "boat status", "entities");
    }

    @Override
    public Boat.Status get(Boat boat) {
        return boat.getStatus();
    }

}