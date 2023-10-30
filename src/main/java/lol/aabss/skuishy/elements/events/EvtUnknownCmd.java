package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
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
@Name("Player - On Unknown Command")
@Description("Thrown when a player executes a command that is not defined.")
@Examples({
        "on unknown command:",
        "\tset unknown command message to \"that doesnt exist\""
})
@Since("1.3")
@RequiredPlugins("Paper 1.13+")
public class EvtUnknownCmd extends SkriptEvent {
    static {
        Skript.registerEvent("unknown command", SimpleEvent.class, UnknownCommandEvent.class, "[on] unknown command");
        EventValues.registerEventValue(UnknownCommandEvent.class, CommandSender.class, new Getter<CommandSender, UnknownCommandEvent>() {
            @Override
            public CommandSender get(UnknownCommandEvent e) {
                return e.getSender();
            }
        }, 0);

        EventValues.registerEventValue(UnknownCommandEvent.class, String.class, new Getter<String, UnknownCommandEvent>() {
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
        return "Player unknown command event";
    }

}
