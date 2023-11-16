package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
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

public class EvtShieldBlock extends SkriptEvent {

    static{
        Skript.registerEvent(
                "On Shield Block",
                SimpleEvent.class,
                ShieldBreakEvent.class,
                "[on] [player] shield (block|disable|break)"
        );
        EventValues.registerEventValue(ShieldBreakEvent.class, Player.class, new Getter<Player, ShieldBreakEvent>() {
            @Override
            public Player get(ShieldBreakEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "shield block event";
    }


}
