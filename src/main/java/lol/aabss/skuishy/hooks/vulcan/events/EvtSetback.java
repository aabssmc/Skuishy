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
import me.frep.vulcan.api.event.VulcanSetbackEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("Vulcan - On Player Setback")
@Description("Called when a player gets setback by vulcan.")
@Examples({
        "on setback:"
})
@Since("1.9")
public class EvtSetback extends SkriptEvent {

    static {
        Skript.registerEvent("on vulcan setback event", EvtSetback.class, VulcanSetbackEvent.class,
                "[vulcan] [player] setback"
        );
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
