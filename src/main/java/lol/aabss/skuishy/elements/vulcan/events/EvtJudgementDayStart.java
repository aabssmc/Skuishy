package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import lol.aabss.skuishy.elements.vulcan.VulcanHook;
import me.frep.vulcan.api.event.VulcanJudgementDayStartEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public class EvtJudgementDayStart extends SkriptEvent {

    static {
        if (VulcanHook.vulcanEnabled()) {
            Skript.registerEvent("Vulcan - Judgement Day Start", EvtJudgementDayStart.class, VulcanJudgementDayStartEvent.class,
                    "[vulcan] judge[ment] [day] start[ed]"
            )
                    .description("Called when the judgement day starts.")
                    .examples(
                            "on judge day start:",
                            "\tsend title \"&cJudgement day has started! &4Beware!\"",
                            "\tapply infinite blindness to all players"
                    )
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        if (event != null) return event.getEventName();
        return "vulcan judgement day start";
    }
}
