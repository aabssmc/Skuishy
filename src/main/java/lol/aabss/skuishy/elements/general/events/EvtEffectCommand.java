package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.command.EffectCommandEvent;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class EvtEffectCommand extends SkriptEvent {

    static {
        Skript.registerEvent("Other - Effect Command", EvtEffectCommand.class, EffectCommandEvent.class,
                "[s(k|c)ript] effect command"
        )
                .description("Called when someone uses a skript effect command.")
                .examples("on effect command:", "\tbroadcast \"ok\"")
                .since("1.7");
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
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event event) {
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "effect command event";
    }
}
