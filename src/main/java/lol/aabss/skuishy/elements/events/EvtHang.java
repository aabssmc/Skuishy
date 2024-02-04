package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.hanging.HangingEvent;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Player - On Hang")
@Description("Called when a entity gets hung.")
@Examples({
        "on entity hang:",
        "\tbroadcast \"%event-entity% has been hung\""
})
@Since("2.0")

public class EvtHang extends SkriptEvent {

    static {
        Skript.registerEvent("on entity hang", EvtHang.class, HangingEvent.class,
                "[entity] hang[ing]"
        );
        EventValues.registerEventValue(HangingEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(HangingEvent e) {
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
        return "hang event";
    }
}
