package lol.aabss.skuishy.elements.effects.decentholograms;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
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
@Name("Decent Holograms - Delete Hologram")
@Description("Deletes a hologram.")
@Examples({
        "..."
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class EffDeleteHologram extends Effect {

    static{
        Skript.registerEffect(EffDeleteHologram.class,
                "(delete|remove) [(decent [hologram[s]]|dh)] [hologram] %hologram%"
        );
    }

    private Expression<Hologram> hologram;

    @Override
    protected void execute(@NotNull Event e) {
        DHAPI.removeHologram(Objects.requireNonNull(hologram.getSingle(e)).getName());
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
