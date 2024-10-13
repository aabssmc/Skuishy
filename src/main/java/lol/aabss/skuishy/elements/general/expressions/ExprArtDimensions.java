package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Art;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Art - Block Dimensions")
@Description("Gets the dimensions of an art piece.")
@Examples({
        "send block height of {_art}"
})
@Since("2.8")
public class ExprArtDimensions extends SimplePropertyExpression<Art, Integer> {

    static {
        register(ExprArtDimensions.class, Integer.class, "block (height|:width)", "arts");
    }

    private boolean width;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        width = parseResult.hasTag("width");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "art dimensions";
    }

    @Override
    @Nullable
    public Integer convert(Art art) {
        return width ? art.getBlockWidth() : art.getBlockHeight();
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
