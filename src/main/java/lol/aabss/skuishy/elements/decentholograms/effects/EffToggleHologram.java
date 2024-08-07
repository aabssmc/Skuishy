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
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
@Name("Decent Holograms - Toggle Hologram")
@Description("Toggles a hologram.")
@Examples({
        "disable hologram named \"lol\"",
        "enable hologram named \"lol\""
})
@Since("2.0")
@RequiredPlugins("DecentHolograms")

public class EffToggleHologram extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerEffect(EffToggleHologram.class,
                    "(en|:dis)able [(decent [hologram[s]]|dh)] [hologram] %holograms%"
            );
        }
    }

    private Expression<Hologram> hologram;
    private boolean dis;

    @Override
    protected void execute(@NotNull Event event) {
        for (Hologram holo : hologram.getArray(event)) {
            if (dis) {
                holo.disable();
            } else {
                holo.enable();
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "toggle hologram";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        hologram = (Expression<Hologram>) exprs[0];
        dis = parseResult.hasTag("dis");
        return true;
    }
}
