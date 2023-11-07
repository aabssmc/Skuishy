package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Player - Stop Glowing")
@Description("Makes a player stop glowing")
@Examples({
        "make player glow red",
        "wait 10 seconds",
        "make player stop glowing"
})
@Since("1.4")

public class EffStopGlow extends Effect {

    static {
        Skript.registerEffect(EffStopGlow.class,
                "make %players% stop glow[ing]", "[skuishy] remove glowing from %player%"
        );
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parser) {
        player = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug)  {
        return "make " + player + " stop glowing";
    }

    @Override
    protected void execute(@NotNull Event event) {
        Player p = player.getSingle(event);
        assert p != null;
        Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        mainScoreboard.getTeams().forEach(team -> team.removeEntry(p.getName()));
        p.setGlowing(false);
    }
}