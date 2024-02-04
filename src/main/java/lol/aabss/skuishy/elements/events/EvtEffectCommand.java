package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.command.EffectCommandEvent;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Other - Effect Command")
@Description("Called when someone uses a skript effect command.")
@Examples({
        "on effect command:",
        "\tbroadcast \"ok\""
})
@Since("1.7")
public class EvtEffectCommand extends SkriptEvent {

    static {
        Skript.registerEvent("on effect command", EvtEffectCommand.class, EffectCommandEvent.class,
                "[s(k|c)ript] effect command"
        );
        EventValues.registerEventValue(EffectCommandEvent.class, String.class, new Getter<>() {
            @Override
            public String get(EffectCommandEvent e) {
                return e.getCommand();
            }
        }, 0);
        EventValues.registerEventValue(EffectCommandEvent.class, CommandSender.class, new Getter<>() {
            @Override
            public CommandSender get(EffectCommandEvent e) {
                return e.getSender();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?> @NonNull [] args, int matchedPattern, SkriptParser.@NonNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NonNull Event event) {
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "effect command event";
    }
}
