package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Warden;

@Name("Warden - Anger")
@Description("Gets the anger of a Warden.")
@Examples({
        "set {_anger} to anger of {_warden}"
})
@Since("2.8")
public class ExprWardenAnger extends EntityExpression<Warden, Integer> {

    static {
        // TODO: add warden anger from an entity
        register(ExprWardenAnger.class, Integer.class, "warden [:highest] anger", "entities");
    }

    @Override
    public Integer get(Warden warden) {
        return tags.contains("highest") ? warden.getHighestAnger() : warden.getAnger();
    }
}
