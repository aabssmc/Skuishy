package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Timespan - Timespan")
@Description("Returns a timespan.")
@Examples({
        "set {_num} to 10",
        "send {_num} minutes to player"
})
@Since("2.7")
public class ExprTimespan extends SimpleExpression<Timespan> {

    static {
        Skript.registerExpression(ExprTimespan.class, Timespan.class, ExpressionType.COMBINED,
                "%integer% (:(tick|second|minute|hour|day|week|month|year))[s] [%-timespan%]"
        );
    }

    private String timespanString;
    private Expression<Timespan> timespan;
    private Expression<Integer> number;

    @Override
    protected Timespan @NotNull [] get(@NotNull Event event) {
        Integer number = this.number.getSingle(event);
        if (number == null){
            return new Timespan[]{};
        }
        Timespan timespan;
        if (timespanString.equals("tick")) {
            timespan = Timespan.fromTicks_i(number);
        } else {
            timespan = Timespan.parse(number + " " + timespanString);
        }
        if (this.timespan != null){
            Timespan secondTimespan = this.timespan.getSingle(event);
            if (secondTimespan == null) {
                return new Timespan[]{timespan};
            }
            return new Timespan[]{Timespan.fromTicks_i(timespan.getTicks_i()+secondTimespan.getTicks_i())};
        }
        return new Timespan[]{timespan};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Timespan> getReturnType() {
        return Timespan.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "timespan";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        number = (Expression<Integer>) exprs[0];
        timespan = (Expression<Timespan>) exprs[1];
        timespanString = parseResult.tags.get(0);
        return true;
    }
}
