package lol.aabss.skuishy.elements.general.expressions.sounds;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Block - Break Sound")
@Description("Gets the break sound of the block.")
@Examples({
        "send break sound of {_b}"
})
@Since("2.1")
public class ExprBreakSound extends PropertyExpression<Block, String> {

    static {
        if (Skript.methodExists(Block.class, "getBlockSoundGroup")) {
            register(ExprBreakSound.class, String.class,
                    "[block] break[ing] sound",
                    "blocks");
        }
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Block[] source) {
        List<String> sounds = new ArrayList<>();
        for (Block block : source) {
            sounds.add(block.getBlockSoundGroup().getBreakSound().getKey().getKey());
        }
        return sounds.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "break sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }
}
