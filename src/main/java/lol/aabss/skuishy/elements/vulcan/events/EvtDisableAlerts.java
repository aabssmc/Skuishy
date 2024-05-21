package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import me.frep.vulcan.api.event.VulcanDisableAlertsEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtDisableAlerts extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Disable Alerts", EvtDisableAlerts.class, VulcanDisableAlertsEvent.class,
                    "[vulcan] disable alert[s]"
            )
                    .description("Called when the alerts get disabled.")
                    .examples(
                            "on disable alerts:",
                            "\tsend \"%event-player% has disabled vulcan alerts\" to console"
                    )
                    .since("1.9");
            EventValues.registerEventValue(VulcanDisableAlertsEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanDisableAlertsEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(VulcanDisableAlertsEvent.class, Timespan.class, new Getter<>() {
                @Override
                public Timespan get(VulcanDisableAlertsEvent e) {
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
        return "vulcan disable alerts";
    }
}
