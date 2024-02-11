package lol.aabss.skuishy.elements.tickmanager.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("NullableProblems")
@Name("TickManager - TickRate")
@Description("Gets/sets the server's tickrate.")
@Examples({
        "set the tickrate to 1"
})
@Since("1.9")
public class ExprTickRate extends SimpleExpression<Float> {

    static{
        if (Skript.classExists("org.bukkit.ServerTickManager")){
            Skript.registerExpression(ExprTickRate.class, Float.class, ExpressionType.SIMPLE,
                    "[the] tick(s|[( |-)]rate) [of [the] (server|game)]",
                    "[the] (server|game)['s] tick(s|[( |-)]rate)"

            );
        }
    }

    @Override
    protected @Nullable Float[] get(@NotNull Event e) {
        return new Float[]{Bukkit.getServer().getServerTickManager().getTickRate()};
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET){
            return CollectionUtils.array(Float.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null) {
            if (mode == Changer.ChangeMode.SET) {
                Bukkit.getServer().getServerTickManager().setTickRate((Float) delta[0]);
            } else if (mode == Changer.ChangeMode.RESET) {
                Bukkit.getServer().getServerTickManager().setTickRate(20);
            }
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Float> getReturnType() {
        return Float.class;
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
