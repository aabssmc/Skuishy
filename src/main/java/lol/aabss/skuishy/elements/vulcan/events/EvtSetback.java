package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import lol.aabss.skuishy.elements.vulcan.VulcanHook;
import me.frep.vulcan.api.event.VulcanSetbackEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public class EvtSetback extends SkriptEvent {

    static {
        if (VulcanHook.vulcanEnabled()) {
            Skript.registerEvent("Vulcan - Setback", EvtSetback.class, VulcanSetbackEvent.class,
                    "[vulcan] [player] setback"
            )
                    .description("Called when a player gets setback by vulcan.")
                    .examples(
                            "on vulcan setback:",
                            "\tsend \"%event-player% has been setback!\" to console"
                    )
                    .since("1.9");
            EventValues.registerEventValue(VulcanSetbackEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanSetbackEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(VulcanSetbackEvent.class, Timespan.class, new Getter<>() {
                @Override
                public Timespan get(VulcanSetbackEvent e) {
                    return Timespan.fromTicks_i(e.getTimestamp());
                }
            }, 0);
        }
    }

    @Override
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event event) {
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        if (event != null) return event.getEventName();
        return "vulcan setback";
    }
}
