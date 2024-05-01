package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import me.frep.vulcan.api.event.VulcanEnableAlertsEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtEnableAlerts extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Enable Alerts", EvtEnableAlerts.class, VulcanEnableAlertsEvent.class,
                    "[vulcan] enable alert[s]"
            )
                    .description("Called when the alerts get enabled.")
                    .examples(
                            "on enable alerts:",
                            "\tsend \"%event-player% has enabled vulcan alerts\" to console"
                    )
                    .since("1.9");
            EventValues.registerEventValue(VulcanEnableAlertsEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanEnableAlertsEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(VulcanEnableAlertsEvent.class, Timespan.class, new Getter<>() {
                @Override
                public Timespan get(VulcanEnableAlertsEvent e) {
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
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        assert e != null;
        return e.getEventName();
    }
}
