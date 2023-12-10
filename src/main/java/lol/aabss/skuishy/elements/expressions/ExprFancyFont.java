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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Other - Fancy Font")
@Description("Makes texts fancy.")
@Examples({
        "send player's name in tiny caps"
})
@Since("1.7.5")

public class ExprFancyFont extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprFancyFont.class, String.class, ExpressionType.SIMPLE,
                "%strings% in [:really] (tiny|small) (caps|font)"
        );
    }

    private Expression<String> text;
    private boolean really;

    @Override
    protected @Nullable String[] get(@NotNull Event e) {
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
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "fancy font";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        text = (Expression<String>) exprs[0];
        really = parseResult.hasTag("really");
        return true;
    }
}
