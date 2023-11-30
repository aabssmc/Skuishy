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

public class EffDeleteHologram extends Effect {

    Expression<Hologram> hologram;

    static{
        Skript.registerEffect(EffDeleteHologram.class,
                "(delete|remove) [(decent [hologram[s]]|dh)] [hologram] %hologram%"
        );
    }

    @Override
    protected void execute(@NotNull Event e) {
        DHAPI.removeHologram(hologram.getSingle(e).getName());
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "delete hologram";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        hologram = (Expression<Hologram>) exprs[0];
        return true;
    }
}
