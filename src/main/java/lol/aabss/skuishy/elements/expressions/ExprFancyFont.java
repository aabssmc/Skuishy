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
import lol.aabss.skuishy.other.Text;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("Other - Fancy Font")
@Description("Makes texts fancy.")
@Examples({
        "send player's name in tiny caps"
})
@Since("1.7.5")

public class ExprFancyFont extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprFancyFont.class, String.class, ExpressionType.SIMPLE,
                "%strings% (in|with) [:really] (tiny|small) (caps|font)"
        );
    }

    private Expression<String> text;
    private boolean really;

    @Override
    protected @Nullable String[] get(@NonNull Event e) {
        if (really){
            return Text.reallySmallCaps(text.getArray(e));
        }
        return Text.smallCaps(text.getArray(e));
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "fancy font";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        text = (Expression<String>) exprs[0];
        really = parseResult.hasTag("really");
        return true;
    }
}
