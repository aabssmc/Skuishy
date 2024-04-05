package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.skript.util.Timespan;
import me.frep.vulcan.api.event.VulcanFlagEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtVulcanFlag extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Player Flag", EvtVulcanFlag.class, VulcanFlagEvent.class,
                    "[vulcan] [player] flag[ged]"
            )
                    .description("Called when a player gets flagged.")
                    .examples("on vulcan flag:")
                    .since("1.9");
            EventValues.registerEventValue(VulcanFlagEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanFlagEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(VulcanFlagEvent.class, Timespan.class, new Getter<>() {
                @Override
                public Timespan get(VulcanFlagEvent e) {
                    return Timespan.fromTicks_i(e.getTimestamp());
                }
            }, 0);
            EventValues.registerEventValue(VulcanFlagEvent.class, String.class, new Getter<>() {
                @Override
                public String get(VulcanFlagEvent e) {
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
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        assert e != null;
        return e.getEventName();
    }
}
