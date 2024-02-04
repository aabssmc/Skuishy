package lol.aabss.skuishy.hooks.vulcan.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;
@Name("Vulcan - Kurtosis of Player")
@Description("Represents the kurtosis of a player.")
@Since("2.0")
public class ExprKurtosis extends PropertyExpression<Player, Number> {

    static{
        register(ExprKurtosis.class, Number.class,
                "kurtosis",
                "players");
    }

    @Override
    protected Number @NonNull [] get(@NonNull Event event, Player @NonNull [] source) {
        if (VulcanAPI.Factory.getApi() != null) {
            return new Number[]{VulcanAPI.Factory.getApi().getKurtosis(source[0])};
        }
        return new Number[]{};
    }

    @Override
    public @NonNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "kurtosis of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
