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
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

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
                "blocks"
        );
    }

    @Override
    protected Note @NotNull [] get(@NotNull Event e, Block @NotNull [] source) {
        Block[] blocks = getExpr().getArray(e);
        for (Block block : blocks) {
            if (block.getBlockData() instanceof NoteBlock data)
                return new Note[]{data.getNote()};
        }
        return new Note[]{null};
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null && mode == Changer.ChangeMode.SET) {
            Block[] blocks = getExpr().getArray(e);
            for (Block block : blocks) {
                if (block.getBlockData() instanceof NoteBlock data)
                    data.setNote((Note) delta[0]);
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Note.class);
        }
        return null;
    }

    @Override
    public @NotNull Class<Note> getReturnType() {
        return Note.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "note of " + getExpr();
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<Block>) exprs[0]);
        return true;
    }
}
