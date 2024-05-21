package lol.aabss.skuishy.elements.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

@Name("Decent Holograms - Update Hologram")
@Description("Updates a hologram.")
@Examples({
        "update hologram {_holo}"
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class EffUpdateHologram extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerEffect(EffUpdateHologram.class,
                    "[(decent [hologram[s]]|dh)] update [hologram] %holograms%"
            );
        }
    }

    private Expression<Hologram> holo;

    @Override
    protected void execute(@NotNull Event event) {
        for (Hologram holo : this.holo.getArray(event)){
            DHAPI.updateHologram(holo.getName());
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "update hologram";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        holo = (Expression<Hologram>) exprs[0];
        return true;
    }
}
