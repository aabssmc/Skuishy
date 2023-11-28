package lol.aabss.skuishy.elements.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import lol.aabss.skuishy.events.ShieldBreakEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Player - On Shield Break")
@Description("Thrown when someone's shield gets broken.")
@Examples({
        "on shield break:",
        "\tbroadcast \"%player%'s shield broke!!\""
})
@Since("1.5")
public class EvtShieldBreak extends SkriptEvent {

    static{
        Skript.registerEvent("on shield break", SimpleEvent.class, ShieldBreakEvent.class,
                "[player] shield (disable|break)"
        );
        EventValues.registerEventValue(ShieldBreakEvent.class, Player.class, new Getter<Player, ShieldBreakEvent>() {
            @Override
            public Player get(ShieldBreakEvent e) {
                return e.getPlayer();
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
        return "shield block event";
    }


}
