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
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
                "blocks"
        );
    }

    @Override
    protected Instrument @NotNull [] get(@NotNull Event e, Block @NotNull [] source) {
        List<Instrument> instruments = new ArrayList<>();
        for (Block block : getExpr().getArray(e)) {
            if (block.getBlockData() instanceof NoteBlock data)
                instruments.add(data.getInstrument());
        }
        return instruments.toArray(Instrument[]::new);
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && delta != null) {
            Block[] blocks = getExpr().getArray(e);
            for (Block block : blocks) {
                if (block.getBlockData() instanceof NoteBlock data) {
                    data.setInstrument((Instrument) delta[0]);
                }
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Instrument.class);
        }
        return null;
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
