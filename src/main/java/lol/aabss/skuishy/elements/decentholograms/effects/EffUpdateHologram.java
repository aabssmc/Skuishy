package lol.aabss.skuishy.elements.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EffUpdateHologram extends Effect {

    Expression<Hologram> holo;

    static{
        Skript.registerEffect(EffUpdateHologram.class,
                "update [(decent [hologram[s]]|dh)] [hologram] %hologram%"
        );
    }

    @Override
    protected void execute(@NotNull Event e) {
        DHAPI.updateHologram(holo.getSingle(e).getName());
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "update hologram";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        holo = (Expression<Hologram>) exprs[0];
        return true;
    }
}
