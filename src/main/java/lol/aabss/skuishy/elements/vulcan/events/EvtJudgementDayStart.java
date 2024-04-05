package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import me.frep.vulcan.api.event.VulcanJudgementDayStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
public class EvtJudgementDayStart extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Judgement Day Start", EvtJudgementDayStart.class, VulcanJudgementDayStartEvent.class,
                    "[vulcan] judge[ment] [day] start[ed]"
            )
                    .description("Called when the judgement day starts.")
                    .examples("on judge day start:")
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
