package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.command.UnknownCommandEvent;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class EvtUnknownCmd extends SkriptEvent {
    static {
        Skript.registerEvent("Other - Unknown Command", SimpleEvent.class, UnknownCommandEvent.class,
                "unknown command"
        )
                .description("Thrown when someone executes a command that is not defined.")
                .examples("on unknown command:", "\tset unknown command message to \"that doesn't exist\"")
                .since("1.3");
        EventValues.registerEventValue(UnknownCommandEvent.class, CommandSender.class, new Getter<>() {
            @Override
            public CommandSender get(UnknownCommandEvent e) {
                return e.getSender();
            }
        }, 0);

        EventValues.registerEventValue(UnknownCommandEvent.class, String.class, new Getter<>() {
            @Override
            public String get(UnknownCommandEvent e) {
                return e.getCommandLine();
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
        return "player unknown command event";
    }

}
