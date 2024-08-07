package lol.aabss.skuishy.elements.general.conditions.is;

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
import org.jetbrains.annotations.Nullable;
@Name("TickManager - Is Stepping")
@Description("Returns true if the server is stepping.")
@Examples({
        "if the server's tickrate is stepping:"
})
@Since("1.9")
public class CondIsStepping extends Condition {
    static {
        if (Skript.classExists("org.bukkit.ServerTickManager")){
            Skript.registerCondition(CondIsStepping.class,
                    "[the] (server|game)['s] [tick[(s|[( |-)]rate)]] (is|are) stepping",
                    "[the] (server|game)['s] [tick[(s|[( |-)]rate)]] (is|are)( not|n't) stepping"
            );
        }
    }

    private boolean is;

    @Override
    public boolean check(@NotNull Event event) {
        if (is){
            return Bukkit.getServer().getServerTickManager().isStepping();
        }
        return !Bukkit.getServer().getServerTickManager().isStepping();
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "ticks are stepping";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        is = matchedPattern == 0;
        return true;
    }
}
