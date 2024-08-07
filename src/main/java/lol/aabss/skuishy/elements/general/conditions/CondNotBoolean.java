package lol.aabss.skuishy.elements.general.conditions;

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
import org.jetbrains.annotations.Nullable;
@Name("Not Boolean")
@Description("Returns the opposite of specified boolean.")
@Examples({
        "if !player's name is oiiink:",
        "\tsend \"you are free to go\""
})
@Since("1.8")
public class CondNotBoolean extends Condition {

    static {
        Skript.registerCondition(CondNotBoolean.class,
                "(!|not )<.+>"
        );
    }

    private Expression<Boolean> bool;
    private Condition cond;
    

    @Override
    public boolean check(@NotNull Event event) {
        return !cond.check(event);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "not boolean";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        cond = Condition.parse(parseResult.regexes.get(0).group(), "Can't understand this condition: " + parseResult.regexes.get(0).group());
        return cond != null;
    }
}
