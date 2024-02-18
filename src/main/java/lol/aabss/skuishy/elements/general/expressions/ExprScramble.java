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
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Name("Other - Scramble String")
@Description("Scrambles a string.")
@Examples({
        "send scrambled \"abcdefghijklmnopqrstuvwxyz\""
})
@Since("2.0")
public class ExprScramble extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprScramble.class, String.class, ExpressionType.COMBINED,
                "(scramble|randomize)[d] %strings%",
                "%strings% (scramble|randomize)[d]"
        );
    }

    private Expression<String> string;
    
    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event e) {
        List<String> strings = new ArrayList<>();
        for (String s: this.string.getArray(e)){
            char[] chars = s.toCharArray();
            Random rand = new Random();
            for (int i = chars.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
            }
            strings.add(new String(chars));
        }
        return strings.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return string.isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "scramble string";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        string = (Expression<String>) exprs[0];
        return true;
    }
}
