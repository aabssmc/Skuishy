package lol.aabss.skuishy.elements.expressions.notes;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Notes - Block Note")
@Description("The note of a note block.")
@Examples({
        "send note of target block"
})
@Since("1.6")

public class ExprBlockNote extends PropertyExpression<Block, Note> {
    static{
        register(ExprBlockNote.class, Note.class,
                "note",
                "block"
        );
    }

    @Override
    protected Note @NonNull [] get(@NonNull Event e, Block @NonNull [] source) {
        Block block = source[0];
        BlockData data = block.getBlockData();
        if (data instanceof NoteBlock){
            return new Note[]{((NoteBlock) data).getNote()};
        }
        return new Note[0];
    }

    @Override
    public void change(@NonNull Event e, @Nullable Object[] delta, Changer.@NonNull ChangeMode mode) {
        Block block = getExpr().getSingle(e);
        if (delta != null && block != null) {
            BlockData data = block.getBlockData();
            if (mode == Changer.ChangeMode.SET) {
                if (data instanceof NoteBlock) {
                    ((NoteBlock) data).setNote((Note) delta[0]);
                }
            }
        }
    }

    @Override
    public Class<?> @NonNull [] acceptChange(final Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Note.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public @NonNull Class<Note> getReturnType() {
        return Note.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "note of " + getExpr();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<Block>) exprs[0]);
        return true;
    }
}
