package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class ExprTickRate extends SimpleExpression<Number> {

    static{
        Skript.registerExpression(ExprTickRate.class, Number.class, ExpressionType.SIMPLE,
                "[the] [tick[(s|[( |-)]rate)]] [of [the] server]",
                "[the] [server] [tick[(s|[( |-)]rate)]]"
        );
    }

    @Override
    protected @Nullable Number[] get(@NotNull Event e) {
        return new Number[]{Bukkit.getServerTickManager().getTickRate()};
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET){
            return CollectionUtils.array(Number.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            Bukkit.getServerTickManager().setTickRate((float) delta[0]);
        }
        else if (mode == Changer.ChangeMode.RESET){
            Bukkit.getServerTickManager().setTickRate(20);
        }
        else{
            assert false;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "tick rate";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
