package lol.aabss.skuishy.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CondIsStepping extends Condition {

    static {
        Skript.registerCondition(CondIsStepping.class,
                "[the] (server|game) [tick[(s|[( |-)]rate)]] (is|are) stepping",
                "[the] (server|game) [tick[(s|[( |-)]rate)]] (is|are)( not|n't) stepping"
        );
    }

    private boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        if (is){
            return Bukkit.getServerTickManager().isStepping();
        }
        return !Bukkit.getServerTickManager().isStepping();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "ticks are stepping";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        is = matchedPattern == 0;
        return true;
    }
}
