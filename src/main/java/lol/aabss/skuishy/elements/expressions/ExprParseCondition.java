package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Other - Parse Condition")
@Description("Parses a condition.")
@Examples({
        "set {_v} parse condition \"if player's name is \"\"aabss\"\"\"",
        "if {_v} = true:",
        "\tsend \"hi aabss\" to sender"
})
@Since("2.1")
public class ExprParseCondition extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprParseCondition.class, Boolean.class, ExpressionType.COMBINED,
                "[parse] condition %string%"
        );
    }

    private Expression<String> condition;

    @Override
    protected Boolean @NotNull [] get(@NotNull Event e) {
        String cond = condition.getSingle(e);
        if (cond != null) {
            Condition condition = Condition.parse(cond, "Can't understand this condition: " + cond);
            if (condition != null)
                return new Boolean[]{condition.check(e)};
        }
        return new Boolean[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "parse condition";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        condition = (Expression<String>) exprs[0];
        return true;
    }
}
