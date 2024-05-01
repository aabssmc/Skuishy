package lol.aabss.skuishy.elements.general.effects;

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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

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
                "make %entities% stop glowing",
                "[sk[uishy]] remove glowing from %entities%"
        );
    }

    private Expression<Entity> entity;

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
        for (Entity e : entity.getArray(event)){
            removeTeam(e);
        }
    }

    private static void removeTeam(Entity e){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        for (Team t : scoreboard.getTeams()){
            if (t.getName().contains("team")){
                if (e instanceof Player) {
                    t.removeEntry(e.getName());
                } else{
                    t.removeEntry(String.valueOf(e.getUniqueId()));
                }
            }
            e.setGlowing(false);
        }
    }

}