package lol.aabss.skuishy.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import me.frep.vulcan.api.event.VulcanJudgementDayEndEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Vulcan - On Judgement Day End")
@Description("Called when the judgement day ends.")
@Examples({
        "on judge day end:"
})
@Since("1.9")
public class EvtJudgementDayEnd extends SkriptEvent {

    static {
        Skript.registerEvent("on vulcan judgement day end event", EvtJudgementDayEnd.class, VulcanJudgementDayEndEvent.class,
                "[vulcan] judge[ment] [day] end[ed]"
        );
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
