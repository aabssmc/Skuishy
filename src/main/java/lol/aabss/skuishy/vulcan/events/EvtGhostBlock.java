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
import me.frep.vulcan.api.event.VulcanGhostBlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("Vulcan - On Ghost Block")
@Description("Called when a player is on a ghost block.")
@Examples({
        "on ghost block:"
})
@Since("1.9")
public class EvtGhostBlock extends SkriptEvent {

    static {
        Skript.registerEvent("on vulcan ghost block event", EvtGhostBlock.class, VulcanGhostBlockEvent.class,
                "[vulcan] ghost block"
        );
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
