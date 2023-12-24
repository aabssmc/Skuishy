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
@Name("TickManager - Is Sprinting")
@Description("Returns true if the server is sprinting.")
@Examples({
        "if the server's tickrate is sprinting:"
})
@Since("1.9")
public class CondIsSprinting extends Condition {
    static {
        if (Skript.classExists("org.bukkit.ServerTickManager")) {
            Skript.registerCondition(CondIsSprinting.class,
                    "[the] (server|game)['s] [tick[(s|[( |-)]rate)]] (is|are) sprint[ing]",
                    "[the] (server|game)['s] [tick[(s|[( |-)]rate)]] (is|are)( not|n't) sprint[ing]"
            );
        }
    }

    private boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        if (is){
            return Bukkit.getServer().getServerTickManager().isSprinting();
        }
        return !Bukkit.getServer().getServerTickManager().isSprinting();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "ticks are sprinting";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        is = matchedPattern == 0;
        return true;
    }
}
