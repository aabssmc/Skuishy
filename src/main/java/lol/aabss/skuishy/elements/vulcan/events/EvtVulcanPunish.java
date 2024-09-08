package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import lol.aabss.skuishy.elements.vulcan.VulcanHook;
import me.frep.vulcan.api.event.VulcanPunishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public class EvtVulcanPunish extends SkriptEvent {

    static {
        if (VulcanHook.vulcanEnabled()) {
            Skript.registerEvent("Vulcan - Player Punish", EvtVulcanPunish.class, VulcanPunishEvent.class,
                    "[vulcan] [player] punish[ed]"
            )
                    .description("Called when a player gets punished.")
                    .examples(
                            "on vulcan punish:",
                            "\tsend \"%event-player% will be flagged!\" to all players where [input is op]"
                    )
                    .since("1.9");
            EventValues.registerEventValue(VulcanPunishEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanPunishEvent e) {
                    return e.getPlayer();
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
        return "vulcan punish";
    }
}
