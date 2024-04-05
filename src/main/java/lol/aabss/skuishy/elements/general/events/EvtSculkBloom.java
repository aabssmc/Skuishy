package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.block.SculkBloomEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtSculkBloom extends SkriptEvent {

    static{
        if (Skript.classExists("org.bukkit.event.block.SculkBloomEvent")){
            Skript.registerEvent("Block - Sculk Bloom", SimpleEvent.class, SculkBloomEvent.class,
                    "sculk bloom[ing]"
            )
                    .description("Represents an event triggered when a new cursor is created by a SculkCatalyst.")
                    .examples("on sculk bloom:", "\tbroadcast \"that was really weird, dont do that again\"")
                    .since("1.5");
            EventValues.registerEventValue(SculkBloomEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(SculkBloomEvent e) {
                    return e.getBlock();
                }
            }, 0);
        }
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
        return "sculk bloom event";
    }
}
