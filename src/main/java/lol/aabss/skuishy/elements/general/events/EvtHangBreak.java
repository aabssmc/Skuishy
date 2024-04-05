package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;


public class EvtHangBreak extends SkriptEvent {

    static {
        Skript.registerEvent("Entity - Hang Break", EvtHangBreak.class, HangingBreakEvent.class,
                "[entity] hang[ing] break[ed]",
                "[entity] break[ed] hang[ing]"
        )
                .description("Triggered when a hanging entity is removed")
                .examples("on entity hang break:", "\tcancel event")
                .since("2.0");
        EventValues.registerEventValue(HangingBreakEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(HangingBreakEvent e) {
                return e.getEntity();
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
        return "hang break event";
    }
}
