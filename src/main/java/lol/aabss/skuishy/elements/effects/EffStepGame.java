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
@Name("TickManager - Step Server")
@Description("Makes the server step.")
@Examples({
        "step server for 10 ticks"
})
@Since("1.9")
public class EffStepGame extends Effect {

    static {
        Skript.registerEffect(EffStepGame.class,
                "step [the] (game|server) (by|for) %integer% [tick[(s|[( |-)]rate)]] [if [(game|server) is] frozen]"
        );
    }

    private Expression<Integer> inte;

    @Override
    protected void execute(@NotNull Event e) {
        Bukkit.getServer().getServerTickManager().stepGameIfFrozen(inte.getSingle(e));
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "step game";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        inte = (Expression<Integer>) exprs[0];
        return true;
    }
}
