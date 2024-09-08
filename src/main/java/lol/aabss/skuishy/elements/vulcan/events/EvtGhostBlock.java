package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import lol.aabss.skuishy.elements.vulcan.VulcanHook;
import me.frep.vulcan.api.event.VulcanGhostBlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public class EvtGhostBlock extends SkriptEvent {

    static {
        if (VulcanHook.vulcanEnabled()) {
            Skript.registerEvent("Vulcan - Ghost Block", EvtGhostBlock.class, VulcanGhostBlockEvent.class,
                    "[vulcan] ghost block"
            )
                    .description("Called when a player is on a ghost block.")
                    .examples(
                            "on ghost block:",
                            "\tgive player 1 air",
                            "\tpush player down with force 1"
                    )
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        if (event != null) return event.getEventName();
        return "vulcan ghost block";
    }
}
