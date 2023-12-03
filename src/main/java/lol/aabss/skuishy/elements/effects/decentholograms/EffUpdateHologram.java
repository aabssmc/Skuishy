package lol.aabss.skuishy.elements.effects.decentholograms;

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
import java.util.Objects;

public class EffUpdateHologram extends Effect {

    static{
        Skript.registerEffect(EffUpdateHologram.class,
                "update [(decent [hologram[s]]|dh)] [hologram] %hologram%"
        );
    }

    private Expression<Hologram> holo;

    @Override
    protected void execute(@NotNull Event e) {
        DHAPI.updateHologram(Objects.requireNonNull(holo.getSingle(e)).getName());
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
