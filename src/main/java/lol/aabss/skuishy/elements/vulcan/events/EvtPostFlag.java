package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import me.frep.vulcan.api.event.VulcanPostFlagEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtPostFlag extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Post Flag", EvtPostFlag.class, VulcanPostFlagEvent.class,
                    "[vulcan] (pre|post)[( |-)]flag[ged]"
            )
                    .description("Called before a player gets flagged.")
                    .examples(
                            "on post-flag:",
                            "\tsend \"%event-player% will be flagged!\" to all players where [input is op]",
                            "\tteleport (all players where [gamemode of input = spectator]) to player"
                    )
                    .since("1.9");
            EventValues.registerEventValue(VulcanPostFlagEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanPostFlagEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(VulcanPostFlagEvent.class, Timespan.class, new Getter<>() {
                @Override
                public Timespan get(VulcanPostFlagEvent e) {
                    return Timespan.fromTicks_i(e.getTimestamp());
                }
            }, 0);
            EventValues.registerEventValue(VulcanPostFlagEvent.class, String.class, new Getter<>() {
                @Override
                public String get(VulcanPostFlagEvent e) {
                    return e.getInfo();
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
        return "vulcan post flag";
    }
}
