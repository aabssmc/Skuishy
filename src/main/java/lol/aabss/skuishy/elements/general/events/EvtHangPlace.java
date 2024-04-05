package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class EvtHangPlace extends SkriptEvent {

    static {
        Skript.registerEvent("Entity - Hang Place", EvtHangPlace.class, HangingPlaceEvent.class,
                "[entity] hang[ing] place[d]",
                "[entity] place[d] hang[ing]"
        )
                .description("Triggered when a hanging entity is created in the world")
                .examples("on entity hang place:", "\tcancel event")
                .since("2.0");
        EventValues.registerEventValue(HangingPlaceEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(HangingPlaceEvent e) {
                return e.getEntity();
            }
        }, 0);
        EventValues.registerEventValue(HangingPlaceEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(HangingPlaceEvent e) {
                return e.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(HangingPlaceEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(HangingPlaceEvent e) {
                return e.getBlock();
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
        return "hang place event";
    }
}
