package lol.aabss.skuishy.elements.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Decent Holograms - Hologram Visibility")
@Description("Hides/shows a hologram to a player.")
@Examples({
        "hide hologram named \"secrethologram\" from all players",
        "reveal hologram named \"secrethologram\" to player(\"aabss\")"
})
@Since("2.6")
@RequiredPlugins("DecentHolograms")
public class EffHologramVisibility extends Effect {

    static {
        Skript.registerEffect(EffHologramVisibility.class,
                "(hide) %holograms% from %players%",
                "(show|reveal) %holograms% to %players%"
        );
    }

    private Expression<Hologram> hologram;
    private Expression<Player> player;
    private int pattern;

    @Override
    protected void execute(@NotNull Event event) {
        for (Hologram holo : hologram.getArray(event)) {
            for (Player p : player.getArray(event)) {
                if (pattern == 0) holo.setHidePlayer(p);
                else holo.setShowPlayer(p);
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "hologram visibility for player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        pattern = matchedPattern;
        hologram = (Expression<Hologram>) exprs[0];
        player = (Expression<Player>) exprs[1];
        return true;
    }
}
