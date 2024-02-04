package lol.aabss.skuishy.elements.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Instrument;
import org.bukkit.event.Event;
import org.bukkit.event.block.NotePlayEvent;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
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
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        if (getParser().isCurrentEvent(NotePlayEvent.class)){
            return true;
        }
        Skript.error("'instrument' can not be used outside of Note Play Event");
        return false;
    }

    @Override
    protected Instrument @org.eclipse.jdt.annotation.Nullable [] get(@NonNull Event e) {
        return new Instrument[]{((NotePlayEvent) e).getInstrument()};
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "event instrument";
    }

    @Override
    public void change(@NonNull Event e, @Nullable Object[] delta, Changer.@NonNull ChangeMode mode) {
        if (delta != null) {
            if (mode == Changer.ChangeMode.SET) {
                ((NotePlayEvent) e).setInstrument((Instrument) delta[0]);
            }
        }
    }

    @Override
    public Class<?> @NonNull [] acceptChange(Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Instrument.class);
        }
        return CollectionUtils.array();
    }
}
