package lol.aabss.skuishy.hooks.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import me.frep.vulcan.api.event.VulcanJudgementDayStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Vulcan - On Judgement Day Start")
@Description("Called when the judgement day starts.")
@Examples({
        "on judge day start:"
})
@Since("1.9")
public class EvtJudgementDayStart extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("on vulcan judgement day start event", EvtJudgementDayStart.class, VulcanJudgementDayStartEvent.class,
                    "[vulcan] judge[ment] [day] start[ed]"
            );
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
