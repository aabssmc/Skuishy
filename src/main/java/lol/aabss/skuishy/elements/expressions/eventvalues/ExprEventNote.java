package lol.aabss.skuishy.elements.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Note;
import org.bukkit.event.Event;
import org.bukkit.event.block.NotePlayEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ExprEventNote extends EventValueExpression<Note> {

    static{
        Skript.registerExpression(ExprEventNote.class, Note.class, ExpressionType.SIMPLE,
                "[the] [event-]note"
        );
    }

    public ExprEventNote() {
        super(Note.class);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "event note";
    }

    @Override
    protected Note @org.jetbrains.annotations.Nullable [] get(@NotNull Event e) {
        return new Note[]{((NotePlayEvent) e).getNote()};
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (getParser().isCurrentEvent(NotePlayEvent.class)){
            return true;
        }
        Skript.error("'note' can not be used outside of Note Play Event");
        return false;
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null) {
            if (mode == Changer.ChangeMode.SET) {
                ((NotePlayEvent) e).setNote((Note) delta[0]);
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Note.class);
        }
        return CollectionUtils.array();
    }
}
