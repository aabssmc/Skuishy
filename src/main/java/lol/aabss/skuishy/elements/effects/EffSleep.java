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
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Player - Sleep")
@Description("Makes a player sleep at a specified location.")
@Examples({
        "attempt to make player sleep at {_loc}"
})
@Since("2.0")
public class EffSleep extends Effect {

    static {
        Skript.registerEffect(EffSleep.class,
                "(try|attempt) to make %player% sleep at %location%",
                "(force|forcefully make) %player% [to] sleep at %location%"
        );
    }

    boolean force;
    Expression<Player> player;
    Expression<Location> loc;

    @Override
    protected void execute(@NonNull Event e) {
        Player p = player.getSingle(e);
        Location l = loc.getSingle(e);
        if (p != null && l != null){
            p.sleep(l, force);
        }
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "make player sleep";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        force = matchedPattern == 1;
        player = (Expression<Player>) exprs[0];
        loc = (Expression<Location>) exprs[1];
        return false;
    }
}
