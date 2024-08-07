package lol.aabss.skuishy.elements.general.expressions.eventvalues;

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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("NullableProblems")
@Name("Block - Sculk Charge")
@Description("Gets/sets the sculk charge.")
@Examples({
        "on sculk bloom:",
        "\tset the charge to 100"
})
@Since("2.0")
public class ExprEventCharge extends EventValueExpression<Integer> {

    static{
        if (Skript.classExists("org.bukkit.event.block.SculkBloomEvent")) {
            Skript.registerExpression(ExprEventCharge.class, Integer.class, ExpressionType.SIMPLE,
                    "[the] [event-][sculk[( |-)]]charge"
            );
        }
    }

    public ExprEventCharge() {
        super(Integer.class);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
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
    protected Integer @Nullable [] get(@NotNull Event event) {
        return new Integer[]{((SculkBloomEvent) event).getCharge()};
    }


    @Override
    public void change(@NotNull Event event, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null) {
            if (mode == Changer.ChangeMode.SET) {
                ((SculkBloomEvent) event).setCharge((Integer) delta[0]);
            } else if (mode == Changer.ChangeMode.ADD) {
                ((SculkBloomEvent) event).setCharge(((SculkBloomEvent) event).getCharge() + (Integer) delta[0]);
            }
        }
        if (mode == Changer.ChangeMode.REMOVE) {
            ((SculkBloomEvent) event).setCharge(((SculkBloomEvent) event).getCharge() - (Integer) delta[0]);
        } else if (mode == Changer.ChangeMode.REMOVE_ALL) {
            ((SculkBloomEvent) event).setCharge(0);
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(Integer.class);
        } else if (mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.REMOVE_ALL){
            return CollectionUtils.array();
        }
        return null;
    }
}
