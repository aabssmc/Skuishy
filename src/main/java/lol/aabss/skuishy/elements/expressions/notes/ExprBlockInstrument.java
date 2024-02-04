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
import org.bukkit.Instrument;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Notes - Block Instrument")
@Description("The instrument of a note block.")
@Examples({
        "send instrument of target block"
})
@Since("1.6")

public class ExprBlockInstrument extends PropertyExpression<Block, Instrument> {


    static{
        register(ExprBlockInstrument.class, Instrument.class,
                "instrument",
                "block"
        );
    }

    @Override
    protected Instrument @NotNull [] get(@NotNull Event e, Block @NotNull [] source) {
        Block block = source[0];
        BlockData data = block.getBlockData();
        if (data instanceof NoteBlock){
            return new Instrument[]{((NoteBlock) data).getInstrument()};
        }
        return new Instrument[0];
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        Block block = getExpr().getSingle(e);
        if (delta != null && block != null) {
            BlockData data = block.getBlockData();
            if (mode == Changer.ChangeMode.SET) {
                if (data instanceof NoteBlock) {
                    ((NoteBlock) data).setInstrument((Instrument) delta[0]);
                }
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Instrument.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public @NotNull Class<Instrument> getReturnType() {
        return Instrument.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "instrument of " + getExpr();
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<Block>) exprs[0]);
        return true;
    }
}
