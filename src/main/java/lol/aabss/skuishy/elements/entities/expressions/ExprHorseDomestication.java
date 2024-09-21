package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.AbstractHorse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Domestication")
@Description("Gets/sets the, optionally max, domestication of a horse.")
@Examples({
        "set max domestication of {_horse} to true"
})
@Since("2.8")
public class ExprHorseDomestication extends EntityExpression<AbstractHorse, Integer> {

    static {
        register(ExprHorseDomestication.class, Integer.class, "[horse] [:max] domestication", "entities");
    }

    @Override
    public Integer get(AbstractHorse horse) {
        return tags.contains("max") ? horse.getMaxDomestication() : horse.getDomestication();
    }

    @Override
    public void change(AbstractHorse horse, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("max")) {
                horse.setMaxDomestication(integer);
            } else {
                horse.setDomestication(integer);
            }
        }
    }
}