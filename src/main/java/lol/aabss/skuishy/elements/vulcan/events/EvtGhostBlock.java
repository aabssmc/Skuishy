package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import me.frep.vulcan.api.event.VulcanGhostBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtGhostBlock extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Ghost Block", EvtGhostBlock.class, VulcanGhostBlockEvent.class,
                    "[vulcan] ghost block"
            )
                    .description("Called when a player is on a ghost block.")
                    .examples("on ghost block:")
                    .since("1.9");
            EventValues.registerEventValue(VulcanGhostBlockEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanGhostBlockEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(VulcanGhostBlockEvent.class, Timespan.class, new Getter<>() {
                @Override
                public Timespan get(VulcanGhostBlockEvent e) {
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
