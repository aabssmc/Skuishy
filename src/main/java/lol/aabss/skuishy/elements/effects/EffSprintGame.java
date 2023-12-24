package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("TickManager - Sprint Server")
@Description("Makes the server sprint.")
@Examples({
        "request the game to sprint for 10 ticks"
})
@Since("1.9")
public class EffSprintGame extends Effect {

    static{
        if (Skript.classExists("org.bukkit.ServerTickManager")){
            Skript.registerEffect(EffSprintGame.class,
                    "[request [([the] game|[the] server)] to] sprint (by|for) %integer% [tick[s]]"
            );
        }
    }

    private Expression<Integer> inte;

    @Override
    protected void execute(@NotNull Event e) {
        Bukkit.getServer().getServerTickManager().requestGameToSprint(inte.getSingle(e));
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "sprint by ticks";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        inte = (Expression<Integer>) exprs[0];
        return true;
    }
}
