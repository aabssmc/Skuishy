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

import java.util.Objects;

@Name("Server - Memory")
@Description("Gets the total, free and max ram of the server.")
@Examples({
        "send total ram"
})
@Since("2.2")
public class ExprMemory extends SimpleExpression<Long> {

    static {
        Skript.registerExpression(ExprMemory.class, Long.class, ExpressionType.COMBINED,
                "[all [of]] [the] (total|:free|:max) (mem[ory]|ram) [of [the] server]",
                "[the] server's (total|:free|:max) (mem[ory]|ram)"
        );
    }

    private String size;

    @Override
    protected @Nullable Long @NotNull [] get(@NotNull Event e) {
        if (Objects.equals(size, "free")){
            return new Long[]{Runtime.getRuntime().freeMemory()};
        } else if (Objects.equals(size, "max")){
            return new Long[]{Runtime.getRuntime().maxMemory()};
        } return new Long[]{Runtime.getRuntime().totalMemory()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "memory of server";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.hasTag("free")){
            size = "free";
        } else if (parseResult.hasTag("max")){
            size = "max";
        } return true;
    }
}
