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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Name("Other - Compact Timespan")
@Description("Sends the timespan neatly.")
@Examples({
        "send neat 10 hours and 23 minutes and 97 seconds # 10h 24m"
})
@Since("2.3")
public class ExprCompactTime extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCompactTime.class, String.class, ExpressionType.COMBINED,
                "(compact[ed]|neat) %timespans%"
        );
    }

    private Expression<Timespan> time;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event event) {
        List<String> timespans = new ArrayList<>();
        for (Timespan time : this.time.getArray(event)){
            String timestring = time.toString().replaceAll("and", "");
            timestring = timestring.replaceAll(" days", "d");
            timestring = timestring.replaceAll(" day", "d");
            timestring = timestring.replaceAll(" hours", "h");
            timestring = timestring.replaceAll(" hour", "h");
            timestring = timestring.replaceAll(" minutes", "m");
            timestring = timestring.replaceAll(" minute", "m");
            timestring = timestring.replaceAll(" seconds", "s");
            timestring = timestring.replaceAll(" second", "s");
            timestring = timestring.replaceAll(" {2}", " ");
            List<String> times = Arrays.stream(timestring.split("\\.")).toList();
            timestring = times.get(0) + times.get(1).substring(times.get(1).length() - 1);
            timespans.add(timestring);
        }
        return timespans.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return time.isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "compact timespan";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        time = (Expression<Timespan>) exprs[0];
        return true;
    }
}
