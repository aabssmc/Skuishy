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
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;
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
    protected @Nullable Number[] get(@NonNull Event e) {
        try {
            if (letter) {
                return new Number[]{NumberFormat.getCompactNumberInstance().parse(num.getSingle(e).toUpperCase())};
            }
            return new Number[]{NumberFormat.getNumberInstance().parse(num.getSingle(e).toUpperCase())};
        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NonNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "number unformat";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        letter = matchedPattern == 0;
        num = (Expression<String>) exprs[0];
        return true;
    }
}
