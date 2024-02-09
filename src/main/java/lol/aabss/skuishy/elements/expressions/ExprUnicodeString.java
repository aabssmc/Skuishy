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
import org.apache.commons.text.StringEscapeUtils;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("String - Unicode String")
@Description("Replaces all the unicode in the string with the symbol.")
@Examples({
        "send \"\\u0048\\u0065\\u006C\\u006C\\u006F\" to player # returns 'Hello'"
})
@Since("1.9")
public class ExprUnicodeString extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprUnicodeString.class, String.class, ExpressionType.COMBINED,
                "uni[code][ ][(string|text)] %strings%"
        );
    }

    private Expression<String> string;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event e) {
        List<String> strings = new ArrayList<>();
        for (String s : string.getArray(e)){
            strings.add(StringEscapeUtils.unescapeJava(s));
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
        return "unicode string";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        string = (Expression<String>) exprs[0];
        return true;
    }
}
