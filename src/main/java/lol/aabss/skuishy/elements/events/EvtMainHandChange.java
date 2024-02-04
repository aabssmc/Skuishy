package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.eclipse.jdt.annotation.NonNull;

@SuppressWarnings("unused")
@Name("Player - On Main Hand Change")
@Description("Called when a player changes their main hand in the client settings.")
@Examples({
        "on main hand change:",
        "\tif event-string is \"left\":",
        "\t\tsend \"eww! weirdo!!!\" to player"
})
@Since("1.3")
public class EvtMainHandChange extends SkriptEvent {
    static {
        Skript.registerEvent("on main hand change", SimpleEvent.class, PlayerChangedMainHandEvent.class,
                "[player] main[(-| )]hand (switch|swap|change)"
        );
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
    public boolean init(Literal<?> @NonNull [] args, int matchedPattern, @NonNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NonNull Event e) {
        return true;
    }

    @Override
    public @NonNull String toString(Event e, boolean debug) {
        return "player main hand change";
    }

}
