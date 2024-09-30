package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Wolf;

@Name("Wolf - Tail Angle")
@Description("Gets the tail angle of a wolf.")
@Examples({
        "send \"%tail angle of {_wolf}%\""
})
@Since("2.8")
public class ExprWolfTailAngle extends EntityExpression<Wolf, Float> {

    static {
        register(ExprWolfTailAngle.class, Float.class, "[wolf] tail angle", "entities");
    }

    @Override
    public Float get(Wolf wolf) {
        return wolf.getTailAngle();
    }
}
