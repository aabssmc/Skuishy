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
import java.text.NumberFormat;
import java.text.ParseException;

@SuppressWarnings("NullableProblems")
@Name("Other - Number Unformat")
@Description("Unformats a number.")
@Examples({
        "send \"10k\" unformatted by letters # -> 10000",
        "send \"10m\" unsplit by commas # -> 10000000"
})
@Since("1.9.5")

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
    protected @Nullable Number[] get(@NotNull Event event) {
        String num = this.num.getSingle(event);
        if (num != null) {
            try {
                if (letter) {
                    return new Number[]{NumberFormat.getCompactNumberInstance().parse(num)};
                }
                return new Number[]{NumberFormat.getNumberInstance().parse(num)};
            } catch (ParseException ee){
                return new Number[]{0};
            }
        } return new Number[]{null};
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "number unformat";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        letter = matchedPattern == 0;
        num = (Expression<String>) exprs[0];
        return true;
    }
}
