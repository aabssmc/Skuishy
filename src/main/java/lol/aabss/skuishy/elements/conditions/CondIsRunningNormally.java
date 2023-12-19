package lol.aabss.skuishy.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("TickManager - Is Running Normally")
@Description("Returns true if the server is running normally.")
@Examples({
        "if the server's tickrate is normal:"
})
@Since("1.9")
public class CondIsRunningNormally extends Condition {

    static {
        Skript.registerCondition(CondIsRunningNormally.class,
                "[the] (server|game)['s] [tick[(s|[( |-)]rate)]] (is|are) [running] normal[ly]",
                "[the] (server|game)['s] [tick[(s|[( |-)]rate)]] (is|are)( n't|not) [running] normal[ly]"
        );
    }

    private boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        if (is){
            return Bukkit.getServer().getServerTickManager().isRunningNormally();
        }
        return !Bukkit.getServer().getServerTickManager().isRunningNormally();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "ticks are running normally";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        is = matchedPattern == 0;
        return true;
    }
}
