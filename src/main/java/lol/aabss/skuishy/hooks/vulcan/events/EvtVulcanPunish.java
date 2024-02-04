package lol.aabss.skuishy.hooks.vulcan.events;

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
import me.frep.vulcan.api.event.VulcanPunishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;
@Name("Vulcan - On Vulcan Punish")
@Description("Called when a player gets punished.")
@Examples({
        "on vulcan punish:"
})
@Since("1.9")
public class EvtVulcanPunish extends SkriptEvent {

    static {
        Skript.registerEvent("on vulcan punish event", EvtVulcanPunish.class, VulcanPunishEvent.class,
                "[vulcan] punish[ed]"
        );
        EventValues.registerEventValue(VulcanPunishEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(VulcanPunishEvent e) {
                return e.getPlayer();
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
        assert e != null;
        return e.getEventName();
    }
}
