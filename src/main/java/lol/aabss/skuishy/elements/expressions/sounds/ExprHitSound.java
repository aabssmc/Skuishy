package lol.aabss.skuishy.elements.expressions.sounds;

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
import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Block - Hit Sound")
@Description("Gets the hit sound of the block.")
@Examples({
        "send hit sound of {_b}"
})
@Since("2.1")
public class ExprHitSound extends PropertyExpression<Block, String> {

    static {
        if (Skript.methodExists(Block.class, "getBlockSoundGroup")) {
            register(ExprHitSound.class, String.class,
                    "[block] hit sound",
                    "blocks");
        }
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Block[] source) {
        List<String> sounds = new ArrayList<>();
        for (Block block : source) {
            sounds.add(block.getBlockSoundGroup().getHitSound().getKey().getKey());
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
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "hit sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }
}
