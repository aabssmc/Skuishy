package lol.aabss.skuishy.elements.general.events;

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
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Player - On Hanging Place")
@Description("Called when a entity gets hung.")
@Examples({
        "on entity hang place:",
        "\tbroadcast \"%event-player% has hung %event-entity%\""
})
@Since("2.0")

public class EvtHangPlace extends SkriptEvent {

    static {
        Skript.registerEvent("on entity hang place", EvtHangPlace.class, HangingPlaceEvent.class,
                "[entity] hang[ing] place[d]",
                "[entity] place[d] hang[ing]"
        );
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
