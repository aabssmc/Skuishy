package lol.aabss.skuishy.other;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.util.Color;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Glow {
    public static void mainGlow(Entity p, Expression<Color> tc, @NotNull Event e) {
        Color color = tc.getSingle(e);
        assert color != null;
        org.bukkit.Color c = color.asBukkitColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        NamedTextColor ntc = findClosestNTC(red, green, blue);
        setGlow(p, ntc);
    }

    private static NamedTextColor findClosestNTC(int red, int green, int blue) {
        Map<NamedTextColor, int[]> colorMap = createColorMap();

        double minDistance = Double.MAX_VALUE;
        NamedTextColor closestColor = null;

        for (Map.Entry<NamedTextColor, int[]> entry : colorMap.entrySet()) {
            int[] targetColor = entry.getValue();
            int targetRed = targetColor[0];
            int targetGreen = targetColor[1];
            int targetBlue = targetColor[2];

            double distance = calculateColorDistance(red, green, blue, targetRed, targetGreen, targetBlue);

            if (distance < minDistance) {
                minDistance = distance;
                closestColor = entry.getKey();
            }
        }
        return closestColor != null ? closestColor : NamedTextColor.WHITE;
    }

    private static Map<NamedTextColor, int[]> createColorMap() {
        Map<NamedTextColor, int[]> colorMap = new HashMap<>();

        colorMap.put(NamedTextColor.BLACK, new int[]{0, 0, 0});
        colorMap.put(NamedTextColor.DARK_BLUE, new int[]{0, 0, 170});
        colorMap.put(NamedTextColor.DARK_GREEN, new int[]{0, 170, 0});
        colorMap.put(NamedTextColor.DARK_AQUA, new int[]{0, 170, 170});
        colorMap.put(NamedTextColor.DARK_RED, new int[]{170, 0, 0});
        colorMap.put(NamedTextColor.DARK_PURPLE, new int[]{170, 0, 170});
        colorMap.put(NamedTextColor.GOLD, new int[]{255, 170, 0});
        colorMap.put(NamedTextColor.GRAY, new int[]{170, 170, 170});
        colorMap.put(NamedTextColor.DARK_GRAY, new int[]{85, 85, 85});
        colorMap.put(NamedTextColor.BLUE, new int[]{85, 85, 255});
        colorMap.put(NamedTextColor.GREEN, new int[]{85, 255, 85});
        colorMap.put(NamedTextColor.AQUA, new int[]{85, 255, 255});
        colorMap.put(NamedTextColor.RED, new int[]{255, 85, 85});
        colorMap.put(NamedTextColor.LIGHT_PURPLE, new int[]{255, 85, 255});
        colorMap.put(NamedTextColor.YELLOW, new int[]{255, 255, 85});
        colorMap.put(NamedTextColor.WHITE, new int[]{255, 255, 255});

        return colorMap;
    }

    private static double calculateColorDistance(int r1, int g1, int b1, int r2, int g2, int b2) {
        return Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
    }

    public static void setGlow(Entity p, NamedTextColor c) {
        Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        mainScoreboard.getTeams().forEach(team -> {
            ChatColor teamColor = getTeamColor(team);
            if (teamColor != null) {
                team.removeEntity(p);
            }
        });
        Team glow = mainScoreboard.getTeam(c + "team");
        if (glow == null) {
            glow = mainScoreboard.registerNewTeam(c + "team");
            glow.color(c);
        }
        glow.addEntity(p);
        p.setGlowing(true);
    }


    private static ChatColor getTeamColor(Team team) {
        String teamName = team.getName();
        ChatColor color = null;
        for (ChatColor chatColor : ChatColor.values()) {
            if (teamName.startsWith(chatColor.toString())) {
                color = chatColor;
                break;
            }
        }

        return color;
    }
}
