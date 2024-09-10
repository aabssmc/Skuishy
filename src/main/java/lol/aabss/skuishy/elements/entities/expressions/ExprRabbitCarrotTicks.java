package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Rabbit;
import org.jetbrains.annotations.Nullable;

@Name("Rabbit - More Carrot Ticks")
@Description("Gets/sets the more carrot ticks of a rabbit.")
@Examples({
        "set more carrot ticks of {_rabbit} to 20 # 1 second"
})
@Since("2.8")
public class ExprRabbitCarrotTicks extends EntityExpression<Rabbit, Integer> {

    static {
        register(ExprRabbitCarrotTicks.class, Integer.class, "[rabbit] more carrot ticks", "entities");
    }

    @Override
    public Integer get(Rabbit rabbit) {
        return rabbit.getMoreCarrotTicks();
    }

    @Override
    public void change(Rabbit rabbit, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            rabbit.setMoreCarrotTicks(integer);
        }
    }
}