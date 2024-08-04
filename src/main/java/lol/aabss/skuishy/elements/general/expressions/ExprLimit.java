package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Other - Max/Min Limits")
@Description("Gets the maximum or minimum limits of a byte, double, float, integer, long, short, potion or enchantment.")
@Examples({
        "send integer limit"
})
@Since("2.8")
public class ExprLimit extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprLimit.class, Number.class, ExpressionType.SIMPLE,
                "[the] [(max|:min)imum] byte limit",
                "[the] [(max|:min)imum] double limit",
                "[the] [(max|:min)imum] float limit",
                "[the] [(max|:min)imum] int[eger] limit",
                "[the] [(max|:min)imum] long limit",
                "[the] [(max|:min)imum] short limit",
                "[the] [(max|:min)imum] (potion [amplifier]|enchant[ment] [level]) limit",
                "[the] [(max|:min)imum] (minecraft|other|health) limit"
        );
    }

    private int matchedPattern;
    private boolean minimum;

    @Override
    protected Number @NotNull [] get(@NotNull Event e) {
        Number num = 0;
        if (matchedPattern == 0){
            num = minimum ? Byte.MIN_VALUE : Byte.MAX_VALUE;
        } else if (matchedPattern == 1) {
            num = minimum ? Double.MIN_VALUE : Double.MAX_VALUE;
        } else if (matchedPattern == 2) {
            num = minimum ? Float.MIN_VALUE : Float.MAX_VALUE;
        } else if (matchedPattern == 3) {
            num = minimum ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else if (matchedPattern == 4) {
            num = minimum ? Long.MIN_VALUE : Long.MAX_VALUE;
        } else if (matchedPattern == 5) {
            num = minimum ? Short.MIN_VALUE : Short.MAX_VALUE;
        } else if (matchedPattern == 6) {
            num = minimum ? 0 : 255;
        } else if (matchedPattern == 7) {
            num = minimum ? 0 : 2048;
        }
        return new Number[]{num};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return (minimum ? "minimum" : "maximum")+" limits ("+matchedPattern+")";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        this.matchedPattern = matchedPattern;
        this.minimum = parseResult.hasTag("min");
        return true;
    }
}
