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

import static lol.aabss.skuishy.Skuishy.start;

@SuppressWarnings("NullableProblems")
@Name("Server - Uptime")
@Description("Gets the server's uptime.")
@Examples({
        "send uptime of server"
})
@Since("1.9")
public class ExprUptime extends SimpleExpression<Timespan> {

    static{
        Skript.registerExpression(ExprUptime.class, Timespan.class, ExpressionType.SIMPLE,
                "[the] server['s] uptime",
                "[the] uptime [of [the] server]"
        );
    }

    @Override
    protected @Nullable Timespan[] get(@NotNull Event event) {
        long uptime = (System.currentTimeMillis()/50) - start;
        return new Timespan[]{Timespan.fromTicks_i(uptime)};
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
        return "uptime";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
