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
import org.jetbrains.annotations.NotNull;

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
                "(try|attempt) to make %players% sleep at %location%",
                "(force|forcefully make) %players% [to] sleep at %location%"
        );
    }

    boolean force;
    Expression<Player> player;
    Expression<Location> loc;

    @Override
    protected void execute(@NotNull Event e) {
        Player[] p = player.getArray(e);
        Location l = loc.getSingle(e);
        if (l != null) {
            for (Player player : p){
                player.sleep(l, force);
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "make player sleep";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        force = matchedPattern == 1;
        player = (Expression<Player>) exprs[0];
        loc = (Expression<Location>) exprs[1];
        return false;
    }
}
