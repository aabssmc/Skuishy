package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class EvtMainHandChange extends SkriptEvent {
    static {
        Skript.registerEvent("Player - Main Hand Change", SimpleEvent.class, PlayerChangedMainHandEvent.class,
                "[player] main[(-| )]hand (switch|swap|change)"
        )
                .description("Called when a player changes their main hand in the client settings.")
                .examples("on main hand change:", "\tif event-string is \"left\":", "\t\tsend \"eww! weirdo!!!\" to player")
                .since("1.3");
        EventValues.registerEventValue(PlayerChangedMainHandEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerChangedMainHandEvent e) {
                return e.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(PlayerChangedMainHandEvent.class, String.class, new Getter<>() {
            @Override
            public String get(PlayerChangedMainHandEvent e) {
                return e.getMainHand().toString().toLowerCase();
            }
        }, 0);


    }

    @Override
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, @NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        return true;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "player main hand change";
    }

}
