package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Piglin;

@Name("Piglin - Dancing")
@Description("Gets/sets the dancing state of a piglin.")
@Examples({
        "set dancing state of {_piglin} to false"
})
@Since("2.8")
public class ExprPiglinDancing extends EntityExpression<Piglin, Object> {

    static {
        register(ExprPiglinDancing.class, Object.class, "[piglin] dancing [state|mode]", "entities");
    }

    @Override
    public Object get(Piglin piglin) {
        return piglin.isDancing();
    }

    @Override
    public void change(Piglin piglin, Object object, Changer.ChangeMode mode) {
        if (object != null && mode == Changer.ChangeMode.SET) {
            if (object instanceof Long) {
                piglin.setDancing((Long) object);
            } else if (object instanceof Boolean) {
                piglin.setDancing((Boolean) object);
            }
        }
    }
}
