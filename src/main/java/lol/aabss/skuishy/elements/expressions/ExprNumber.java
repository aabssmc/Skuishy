package lol.aabss.skuishy.elements.expressions;

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

import javax.annotation.Nullable;
import java.text.NumberFormat;
import java.text.ParseException;

@Name("Other - Number Format")
@Description("Turns a formatted number into a normal number.")
@Examples({
        "send 10K # -> 10000",
        "send 20M # -> 20000000"
})
@Since("2.0")

public class ExprNumber extends SimpleExpression<Number> {

    static{
        Skript.registerExpression(ExprNumber.class, Number.class, ExpressionType.COMBINED,
                "%number%(:k|:m|:b|:t)"
        );
    }

    private Expression<Number> num;
    private String let;

    @Override
    protected @Nullable Number[] get(@NotNull Event e) {
        try {
            return new Number[]{NumberFormat.getCompactNumberInstance().parse(num.getSingle(e) + let.toUpperCase())};
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
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
        return "number format";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        num = (Expression<Number>) exprs[0];
        if (parseResult.hasTag("k")) let = "k";
        else if (parseResult.hasTag("m")) let = "m";
        else if (parseResult.hasTag("b")) let = "b";
        else if (parseResult.hasTag("t")) let = "t";
        return true;
    }
}
