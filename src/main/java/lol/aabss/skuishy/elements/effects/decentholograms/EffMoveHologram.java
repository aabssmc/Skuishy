package lol.aabss.skuishy.elements.effects.decentholograms;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class EffMoveHologram extends Effect {

    static{
        Skript.registerEffect(EffMoveHologram.class,
                "(move|teleport) [(decent [hologram[s]]|dh)] [hologram] %hologram% to %location%"
        );
    }

    private Expression<Hologram> hologram;
    private Expression<Location> location;

    @Override
    protected void execute(@NotNull Event e) {
        DHAPI.moveHologram(Objects.requireNonNull(hologram.getSingle(e)), Objects.requireNonNull(location.getSingle(e)));
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "move hologram";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        hologram = (Expression<Hologram>) exprs[0];
        location = (Expression<Location>) exprs[1];
        return true;
    }
}
