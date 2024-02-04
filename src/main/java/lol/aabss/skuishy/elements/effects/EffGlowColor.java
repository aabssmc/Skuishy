package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Color;
import ch.njol.util.Kleenean;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Glow Color")
@Description("Makes a entity glow a color")
@Examples({
        "make player glow red",
        "wait 10 seconds",
        "make player stop glowing "
})
@Since("1.4")

public class EffGlowColor extends Effect {

    static {
        Skript.registerEffect(EffGlowColor.class,
                "make %entities% glow %color%"
        );
    }

    private Expression<Entity> entity;
    private Expression<Color> color;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parser) {
        entity = (Expression<Entity>) expressions[0];
        color = (Expression<Color>) expressions[1];
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug)  {
        return "make " + entity.toString(event, debug) + " glowing " + color.toString(event, debug);
    }

    @Override
    protected void execute(@NotNull Event event) {
        for(Entity e : entity.getArray(event)){
            Color color = this.color.getSingle(event);
            if (color != null) {
                org.bukkit.Color c = color.asBukkitColor();
                NamedTextColor ntc = NamedTextColor.nearestTo(TextColor.color(c.getRed(), c.getGreen(), c.getBlue()));
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                Team glow = scoreboard.getTeam(c + "color");
                if (glow == null) {
                    glow = scoreboard.registerNewTeam(c + "color");
                    glow.color(ntc);
                }
                glow.addEntity(e);
                e.setGlowing(true);
            }
        }
    }
}