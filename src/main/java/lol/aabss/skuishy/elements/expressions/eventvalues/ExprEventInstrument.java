package lol.aabss.skuishy.elements.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Instrument;
import org.bukkit.event.Event;
import org.bukkit.event.block.NotePlayEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ExprEventInstrument extends EventValueExpression<Instrument> {

    static{
        Skript.registerExpression(ExprEventInstrument.class, Instrument.class, ExpressionType.SIMPLE,
                "[the] [event-]instrument"
        );
    }

    public ExprEventInstrument() {
        super(Instrument.class);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "event instrument";
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            assert delta != null;
            ((NotePlayEvent) e).setInstrument((Instrument) delta[0]);
        }
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Instrument.class);
        }
        return CollectionUtils.array();
    }
}
