package lol.aabss.skuishy.hooks.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
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
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;
@Name("Vulcan - On Disable Alerts")
@Description("Called when the alerts get disabled.")
@Examples({
        "on disable alerts:"
})
@Since("1.9")
public class EvtDisableAlerts extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("on vulcan disable alerts event", EvtDisableAlerts.class, VulcanDisableAlertsEvent.class,
                    "[vulcan] disable alert[s]"
            );
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
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        assert e != null;
        return e.getEventName();
    }
}
