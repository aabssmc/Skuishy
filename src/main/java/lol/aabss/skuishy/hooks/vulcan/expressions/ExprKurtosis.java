package lol.aabss.skuishy.hooks.vulcan.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static lol.aabss.skuishy.hooks.vulcan.Vulcan.vulcan;

@Name("Vulcan - Kurtosis of Player")
@Description("Represents the kurtosis of a player.")
@Since("2.0")
public class ExprKurtosis extends PropertyExpression<Player, Number> {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            register(ExprKurtosis.class, Number.class,
                    "kurtosis",
                    "players");
        }
    }

    @Override
    protected Number @NotNull [] get(@NotNull Event event, Player @Nullable [] source) {
        if (source != null) {
            List<Number> kurtosis = new ArrayList<>();
            for (Player p : source) {
                kurtosis.add(vulcan().getKurtosis(p));
            }
            return kurtosis.toArray(Number[]::new);
        }
        return new Number[]{null};
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "kurtosis of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
