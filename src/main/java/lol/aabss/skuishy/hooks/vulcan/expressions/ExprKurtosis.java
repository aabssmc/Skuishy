package lol.aabss.skuishy.hooks.vulcan.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;
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
        if (source != null && VulcanAPI.Factory.getApi() != null) {
            for (Player p : source) {
                return new Number[]{VulcanAPI.Factory.getApi().getKurtosis(p)};
            }
        }
        return new Number[]{null};
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
