package lol.aabss.skuishy.elements.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Decent Holograms - Realign Lines")
@Description("Realigns lines of a hologram.")
@Examples({
        "realign lines of hologram {_holo}"
})
@Since("2.5")
@RequiredPlugins("DecentHolograms")

public class EffRealignLines extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerEffect(EffRealignLines.class,
                    "[(decent [hologram[s]]|dh)] [re]align lines of [hologram] %holograms%"
            );
        }
    }

    private Expression<Hologram> holo;

    @Override
    protected void execute(@NotNull Event e) {
        for (Hologram holo : this.holo.getArray(e)){
            holo.realignLines();
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "realign lines of hologram";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        holo = (Expression<Hologram>) exprs[0];
        return true;
    }
}
