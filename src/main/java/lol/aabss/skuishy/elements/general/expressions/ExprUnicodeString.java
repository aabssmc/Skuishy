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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Name("String - Unicode String")
@Description("Replaces all the unicode in the string with the symbol.")
@Examples({
        "send \"\\u0048\\u0065\\u006C\\u006C\\u006F\" to player # returns 'Hello'"
})
@Since("2.2")
public class ExprUnicodeString extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprUnicodeString.class, String.class, ExpressionType.COMBINED,
                "uni[code][ ][(string|text)] %strings%"
        );
    }

    private Expression<String> string;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event event) {
        List<String> strings = new ArrayList<>();
        for (String s : string.getArray(event)){
            Pattern pattern = Pattern.compile("\\\\u[0-9a-fA-F]{4}");
            Matcher matcher = pattern.matcher(s);
            StringBuilder decodedString = new StringBuilder();
            while (matcher.find()) {
                String unicodeSequence = matcher.group();
                char unicodeChar = (char) Integer.parseInt(unicodeSequence.substring(2), 16);
                matcher.appendReplacement(decodedString, Character.toString(unicodeChar));
            }
            matcher.appendTail(decodedString);
            strings.add(decodedString.toString());
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "unicode string";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        string = (Expression<String>) exprs[0];
        return true;
    }
}
