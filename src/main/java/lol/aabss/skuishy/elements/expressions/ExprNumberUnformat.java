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

@Name("Other - Number Unformat")
@Description("Unformats a number.")
@Examples({
        "send \"10k\" unformatted by letters # -> 10000",
        "send \"10m\" unsplit by commas # -> 10000000"
})
@Since("2.0")

public class ExprNumberUnformat extends SimpleExpression<Number> {

    static{
        Skript.registerExpression(ExprNumberUnformat.class, Number.class, ExpressionType.COMBINED,
                "%string% (unformatted|unshortened) by letter[s]",
                "%string% (unformatted|unsplit) by comma[s]"
        );
    }

    boolean letter;
    Expression<String> num;

    @Override
    protected @Nullable Number[] get(@NotNull Event e) {
        try {
            if (letter) {
                return new Number[]{NumberFormat.getCompactNumberInstance().parse(num.getSingle(e))};
            }
            return new Number[]{NumberFormat.getNumberInstance().parse(num.getSingle(e))};
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
        return "number unformat";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        letter = matchedPattern == 0;
        num = (Expression<String>) exprs[0];
        return true;
    }
}
