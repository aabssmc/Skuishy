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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("NullableProblems")
@Name("Server - Server IP")
@Description("Gets the IP that this server is bound to, or empty string if not specified.")
@Examples({
        "send host of the server"
})
@Since("2.1")
public class ExprIPofServer extends SimpleExpression<String> {

    static{
        Skript.registerExpression(ExprIPofServer.class, String.class, ExpressionType.SIMPLE,
                "[the] (ip|host) [the] server",
                "[the] server's (ip|host)"
        );
    }


    @Override
    protected @Nullable String[] get(@NotNull Event event) {
        return new String[]{Bukkit.getIp()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "port of server";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
