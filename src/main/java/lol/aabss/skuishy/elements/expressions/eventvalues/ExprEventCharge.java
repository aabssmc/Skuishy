package lol.aabss.skuishy.elements.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.event.block.SculkBloomEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("Block - Sculk Charge")
@Description("Gets/sets the sculk charge.")
@Examples({
        "on sculk bloom:",
        "\tset the charge to 100"
})
@Since("2.0")
public class ExprEventCharge extends EventValueExpression<Integer> {

    static{
        Skript.registerExpression(ExprEventCharge.class, Integer.class, ExpressionType.SIMPLE,
                "[the] [event-][sculk[( |-)]]charge"
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
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (getParser().isCurrentEvent(SculkBloomEvent.class)){
            return true;
        }
        Skript.error("'charge' can not be used outside of Sculk Bloom Event");
        return false;
    }

    @Override
    protected Integer @org.jetbrains.annotations.Nullable [] get(@NotNull Event e) {
        return new Integer[]{((SculkBloomEvent) e).getCharge()};
    }


    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null) {
            if (mode == Changer.ChangeMode.SET) {
                ((SculkBloomEvent) e).setCharge((Integer) delta[0]);
            } else if (mode == Changer.ChangeMode.ADD) {
                ((SculkBloomEvent) e).setCharge(((SculkBloomEvent) e).getCharge() + (Integer) delta[0]);
            } else if (mode == Changer.ChangeMode.REMOVE) {
                ((SculkBloomEvent) e).setCharge(((SculkBloomEvent) e).getCharge() - (Integer) delta[0]);
            } else if (mode == Changer.ChangeMode.REMOVE_ALL) {
                ((SculkBloomEvent) e).setCharge(0);
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.REMOVE_ALL) {
            return CollectionUtils.array(Integer.class);
        }
        return CollectionUtils.array();
    }
}
