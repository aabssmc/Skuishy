package lol.aabss.skuishy.elements.vulcan.events;

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
import ch.njol.skript.util.Timespan;
import me.frep.vulcan.api.event.VulcanPostFlagEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;
@Name("Vulcan - On Vulcan Post Flag")
@Description("Called before a player gets flagged.")
@Examples({
        "on vulcan post flag:"
})
@Since("1.9")
public class EvtPostFlag extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("on vulcan post flag event", EvtPostFlag.class, VulcanPostFlagEvent.class,
                    "[vulcan] (pre|post)[( |-)]flag[ged]"
            );
            EventValues.registerEventValue(VulcanPostFlagEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(VulcanPostFlagEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(VulcanPostFlagEvent.class, Timespan.class, new Getter<>() {
                @Override
                public Timespan get(VulcanPostFlagEvent e) {
                    return Timespan.fromTicks_i(e.getTimestamp());
                }
            }, 0);
            EventValues.registerEventValue(VulcanPostFlagEvent.class, String.class, new Getter<>() {
                @Override
                public String get(VulcanPostFlagEvent e) {
                    return e.getInfo();
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
        assert e != null;
        return e.getEventName();
    }
}
