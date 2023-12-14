package lol.aabss.skuishy.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("Condition And")
@Description("Returns true if both conditions are true.")
@Examples({
        "if size of all players = 10 && size of max players = 100:",
        "\tsend \"wow!!!!!\""
})
@Since("1.8")
public class CondAnd extends Condition {

    static {
        Skript.registerCondition(CondAnd.class,
                "<.+> && <.+>"
        );
    }

    private Condition condition1;
    private Condition condition2;

    @Override
    public boolean check(@NotNull Event e) {
        return condition1.check(e) && condition2.check(e);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "condition and";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        String cond1 = parseResult.regexes.get(0).group();
        String cond2 = parseResult.regexes.get(0).group();
        condition1 = Condition.parse(cond1, "Can't understand this condition: " + cond1);
        condition2 = Condition.parse(cond2, "Can't understand this condition: " + cond2);
        return true;
    }
}
