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
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;
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
    protected void execute(@NonNull Event e) {
        Integer in = inte.getSingle(e);
        if (in != null) {
            Bukkit.getServer().getServerTickManager().requestGameToSprint(in);
        }
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "sprint by ticks";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        inte = (Expression<Integer>) exprs[0];
        return true;
    }
}
