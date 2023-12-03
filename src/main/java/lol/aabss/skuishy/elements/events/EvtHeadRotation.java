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
import lol.aabss.skuishy.events.HeadRotationEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("On Head Rotation")
@Description("Called when a player rotates their head.")
@Examples({
        "on head rotate:"
})
@Since("1.7")
public class EvtHeadRotation extends SkriptEvent {

    static {
        Skript.registerEvent("on head rotation", EvtHeadRotation.class, HeadRotationEvent.class,
                "[player] head rotat(e|ion)"
        );
        EventValues.registerEventValue(HeadRotationEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(HeadRotationEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(HeadRotationEvent.class, Location.class, new Getter<>() {
            @Override
            public Location get(HeadRotationEvent e) {
                return e.getFrom();
            }
        }, -1);
        EventValues.registerEventValue(HeadRotationEvent.class, Location.class, new Getter<>() {
            @Override
            public Location get(HeadRotationEvent e) {
                return e.getTo();
            }
        }, 1);
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
        return "head rotation event";
    }
}
