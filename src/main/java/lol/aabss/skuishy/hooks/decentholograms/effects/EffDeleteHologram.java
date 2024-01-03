package lol.aabss.skuishy.hooks.decentholograms.effects;

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

import javax.annotation.Nullable;
@Name("Decent Holograms - Delete Hologram")
@Description("Deletes a hologram.")
@Examples({
        "..."
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class EffDeleteHologram extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().getPlugin("DecentHolograms") != null) {
            Skript.registerEffect(EffDeleteHologram.class,
                    "(delete|remove) [(decent [hologram[s]]|dh)] [hologram] %hologram%"
            );
        }
    }

    private Expression<Hologram> hologram;

    @Override
    protected void execute(@NotNull Event e) {
        Hologram holo = hologram.getSingle(e);
        if (holo != null) {
            holo.delete();
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "delete hologram";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        hologram = (Expression<Hologram>) exprs[0];
        return true;
    }
}
