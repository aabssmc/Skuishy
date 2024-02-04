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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;
@SuppressWarnings("NullableProblems")
@Name("Server - Server Port")
@Description("Gets the port of the server.")
@Examples({
        "if port of server is 1234:"
})
@Since("2.0")
public class ExprPortOfServer extends SimpleExpression<Integer> {

    static{
        Skript.registerExpression(ExprPortOfServer.class, Integer.class, ExpressionType.SIMPLE,
                "[the] port of [the] server",
                "[the] server's port"
        );
    }


    @Override
    protected @Nullable Integer[] get(@NonNull Event e) {
        return new Integer[]{Bukkit.getPort()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NonNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "port of server";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        return true;
    }
}
