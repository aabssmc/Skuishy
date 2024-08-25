package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.SimpleEntityEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@Name("Entity - Stop Glowing")
@Description("Makes a entity stop glowing")
@Examples({
        "make player glow red",
        "wait 10 seconds",
        "make player stop glowing"
})
@Since("1.4")
public class EffStopGlow extends SimpleEntityEffect<Entity> {

    static {
        Skript.registerEffect(EffStopGlow.class,
                "make %entities% stop glowing",
                "sk[uishy] remove glowing from %entities%"
        );
    }

    @Override
    protected void execute(Entity entity) {
        removeTeam(entity);
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