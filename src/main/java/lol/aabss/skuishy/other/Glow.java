package lol.aabss.skuishy.other;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.util.Color;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class Glow {
    public static void mainGlow(Entity p, Expression<Color> tc, @NotNull Event e) {
        Color color = tc.getSingle(e);
        if (color != null) {
            org.bukkit.Color c = color.asBukkitColor();
            int red = c.getRed();
            int green = c.getGreen();
            int blue = c.getBlue();
            NamedTextColor ntc = NamedTextColor.nearestTo(TextColor.color(red, green, blue));
            Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Team glow = mainScoreboard.getTeam(c + "team");
            if (glow == null) {
                glow = mainScoreboard.registerNewTeam(c + "team");
                glow.color(ntc);
            }
            glow.addEntity(p);
            p.setGlowing(true);
        }
    }
}
