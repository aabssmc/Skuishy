package lol.aabss.skuishy.elements.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.event.block.SculkBloomEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ExprEventCharge extends EventValueExpression<Integer> {

    static{
        Skript.registerExpression(ExprEventCharge.class, Integer.class, ExpressionType.SIMPLE,
                "[the] [event-]integer"
        );
    }

    public ExprEventCharge() {
        super(Integer.class);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "event charge";
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            assert delta != null;
            ((SculkBloomEvent) e).setCharge((Integer) delta[0]);
        }
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Integer.class);
        }
        return CollectionUtils.array();
    }
}
