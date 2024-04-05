package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import me.frep.vulcan.api.event.VulcanViolationResetEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtViolationReset extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Violation Reset", EvtViolationReset.class, VulcanViolationResetEvent.class,
                    "[vulcan] violation[s] reset"
            )
                    .description("Called when all the violations get reset.")
                    .examples("on violation reset:")
                    .since("1.9");
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
