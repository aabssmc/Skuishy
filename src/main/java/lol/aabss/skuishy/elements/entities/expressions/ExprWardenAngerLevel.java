package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Warden;

@Name("Warden - Anger Level")
@Description("Gets the anger level of a warden.")
@Examples({
        "send anger level of {_warden}"
})
@Since("2.8")
public class ExprWardenAngerLevel extends EntityExpression<Warden, Warden.AngerLevel> {

    static {
        register(ExprWardenAngerLevel.class, Warden.AngerLevel.class, "warden anger level", "entities");
    }

    @Override
    public Warden.AngerLevel get(Warden warden) {
        return warden.getAngerLevel();
    }
}
