package lol.aabss.skuishy.elements.expressions.notes;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;
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
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            assert delta != null;
            ((NotePlayEvent) e).setNote((Note) delta[0]);
        } else {
            assert false;
        }
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Note.class);
        }
        return CollectionUtils.array();
    }
}
