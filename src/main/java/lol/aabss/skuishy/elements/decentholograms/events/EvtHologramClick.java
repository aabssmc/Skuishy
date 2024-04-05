package lol.aabss.skuishy.elements.decentholograms.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import eu.decentsoftware.holograms.api.actions.ClickType;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.event.HologramClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class EvtHologramClick extends SkriptEvent {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerEvent("Decent Holograms - Hologram Click", EvtHologramClick.class, HologramClickEvent.class,
                    "[(decent [hologram[s]]|dh)] holo[gram] click"
            )
                    .description("Called when a hologram gets clicked.")
                    .examples("on hologram click:", "\tbroadcast \"%player% clicked %event-hologram%\"")
                    .since("1.7")
                    .requiredPlugins("DecentHolograms");
            EventValues.registerEventValue(HologramClickEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(HologramClickEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(HologramClickEvent.class, ClickType.class, new Getter<>() {
                @Override
                public ClickType get(HologramClickEvent e) {
                    return e.getClick();
                }
            }, 0);
            EventValues.registerEventValue(HologramClickEvent.class, Hologram.class, new Getter<>() {
                @Override
                public Hologram get(HologramClickEvent e) {
                    return e.getHologram();
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
        return "hologram click";
    }
}
