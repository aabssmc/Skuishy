package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EffSprintGame extends Effect {

    static{
        Skript.registerEffect(EffStepGame.class,
                "[request [[the] game|[the] server] to] sprint by %integer% [tick[s]]"
        );
    }

    private Expression<Integer> inte;

    @Override
    protected void execute(@NotNull Event e) {
        Bukkit.getServerTickManager().requestGameToSprint(inte.getSingle(e));
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
