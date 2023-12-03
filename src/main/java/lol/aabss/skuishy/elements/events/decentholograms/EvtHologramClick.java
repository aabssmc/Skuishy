package lol.aabss.skuishy.elements.events.decentholograms;

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
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.event.HologramClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Decent Holograms - Hologram Click")
@Description("Called when a hologram gets clicked.")
@Examples({
        "on hologram click:"
})
@Since("1.7")
public class EvtHologramClick extends SkriptEvent {

    static{
        Skript.registerEvent("hologram click", EvtHologramClick.class, HologramClickEvent.class,
                "[(decent [hologram[s]]|dh)] holo[gram] click"
        );
        EventValues.registerEventValue(HologramClickEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(HologramClickEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(HologramClickEvent.class, ClickType.class, new Getter<>() {
            @Override
            public ClickType get(HologramClickEvent e) {
                return ClickType.valueOf(e.getClick().name());
            }
        }, 0);
        EventValues.registerEventValue(HologramClickEvent.class, Hologram.class, new Getter<>() {
            @Override
            public Hologram get(HologramClickEvent e) {
                return e.getHologram();
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
        return "hologram click";
    }
}
