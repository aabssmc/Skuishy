package lol.aabss.skuishy.elements.expressions.sounds;

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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Block - Place Sound")
@Description("Gets the place sound of the block.")
@Examples({
        "send place sound of {_b}"
})
@Since("2.1")
public class ExprPlaceSound extends PropertyExpression<Block, String> {

    static {
        register(ExprPlaceSound.class, String.class,
                "[block] place sound",
                "block");
    }

    @Override
    protected String @NonNull [] get(@NonNull Event event, Block[] source) {
        return new String[]{source[0].getBlockSoundGroup().getPlaceSound().name().replaceAll("_", ".").toLowerCase()};
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "place sound";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }
}
