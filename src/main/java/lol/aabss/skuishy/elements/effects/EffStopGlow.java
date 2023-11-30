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
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Entity - Stop Glowing")
@Description("Makes a entity stop glowing")
@Examples({
        "make player glow red",
        "wait 10 seconds",
        "make player stop glowing"
})
@Since("1.4")

public class EffStopGlow extends Effect {

    static {
        Skript.registerEffect(EffStopGlow.class,
                "make %entities% stop glowing", "[skuishy] remove glowing from %entities%"
        );
    }

    private Expression<Entity> entity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parser) {
        entity = (Expression<Entity>) expressions[0];
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug)  {
        return "make " + entity.toString(event, debug) + " stop glowing";
    }

    @Override
    protected void execute(@NotNull Event event) {
        for(Entity e : entity.getAll(event)){
            Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            mainScoreboard.getTeams().forEach(team -> team.removeEntity(e));
            e.setGlowing(false);
        }
    }
}