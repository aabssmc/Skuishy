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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

@SuppressWarnings("NullableProblems")
@Name("Other - Centered Text")
@Description("Makes texts centered.")
@Examples({
        "send centered player's name in tiny caps"
})
@Since("1.7.5")

public class ExprCenteredText extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprCenteredText.class, String.class, ExpressionType.SIMPLE,
                "center[ed] %strings%",
                "%strings% center[ed]"
        );
    }

    private Expression<String> text;

    @Override
    protected @Nullable String[] get(@NotNull Event e) {
        List<String> texts = new ArrayList<>();
        for (String text : this.text.getArray(e)) {
            int totalWidth = max(80, text.length() + 4);
            int padSize = (totalWidth - text.length()) / 2;
            texts.add(" ".repeat(padSize) + text);
        }
        return texts.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return text.isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "centered text";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        text = (Expression<String>) exprs[0];
        return true;
    }
}
