package lol.aabss.skuishy.elements.conditions.is;

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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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
    public boolean check(@NonNull Event e) {
        if (is){
            return Bukkit.getServer().getServerTickManager().isStepping();
        }
        return !Bukkit.getServer().getServerTickManager().isStepping();
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "ticks are stepping";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        is = matchedPattern == 0;
        return true;
    }
}
