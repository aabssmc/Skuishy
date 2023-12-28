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
@Name("Other - Number Format")
@Description("Formats a number.")
@Examples({
        "send 10000 formatted by letters # -> 10k",
        "send 10000000 split by commas # -> 10m"
})
@Since("2.0")

public class ExprNumberFormat extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprNumberFormat.class, String.class, ExpressionType.COMBINED,
                "%number% (formatted|shortened) by letter[s]",
                "%number% (formatted|split) by comma[s]"
        );
    }

    boolean letter;
    Expression<Number> num;

    @Override
    protected @Nullable String[] get(@NotNull Event e) {
        if (letter){
            return new String[]{NumberFormat.getCompactNumberInstance().format(num.getSingle(e))};
        }
        return new String[]{NumberFormat.getNumberInstance().format(num.getSingle(e))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "number format";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        letter = matchedPattern == 0;
        num = (Expression<Number>) exprs[0];
        return true;
    }
}
