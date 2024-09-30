package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Anger")
@Description("Gets/sets the anger level of a bee.")
@Examples({
        "set anger of {_bee} to 5"
})
@Since("2.8")
public class ExprBeeAnger extends EntityExpression<Bee, Integer> {

    static {
        register(ExprBeeAnger.class, Integer.class, "[bee] anger", "entities");
    }

    @Override
    public Integer get(Bee bee) {
        return bee.getAnger();
    }

    @Override
    public void change(Bee bee, @Nullable Integer anger, Changer.ChangeMode mode) {
        if (anger != null && mode == Changer.ChangeMode.SET) {
            bee.setAnger(anger);
        }
    }
}
