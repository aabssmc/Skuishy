package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.block.SculkBloomEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EvtSculkBloom extends SkriptEvent {

    static{
        Skript.registerEvent("sculk bloom", EvtSculkBloom.class, SculkBloomEvent.class,
                "sculk bloom[ing]"
        );
        EventValues.registerEventValue(SculkBloomEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(SculkBloomEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(SculkBloomEvent.class,Integer.class, new Getter<>() {
            @Override
            public Integer get(SculkBloomEvent e) {
                return e.getCharge();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "sculk bloom event";
    }
}
