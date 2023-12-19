package lol.aabss.skuishy.vulcan.events;

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
import me.frep.vulcan.api.event.VulcanEnableAlertsEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("Vulcan - On Enable Alerts")
@Description("Called when the alerts get enabled.")
@Examples({
        "on enable alerts:"
})
@Since("1.9")
public class EvtEnableAlerts extends SkriptEvent {

    static {
        Skript.registerEvent("on vulcan enable alerts event", EvtEnableAlerts.class, VulcanEnableAlertsEvent.class,
                "[vulcan] enable alert[s]"
        );
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
