package lol.aabss.skuishy.hooks.vulcan.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
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
@Name("Vulcan - Sensitivity of Player")
@Description("Represents the sensitivity of a player.")
@Examples({
        "send the sensitivity of player"
})
@Since("2.0")
public class ExprSensitivity extends PropertyExpression<Player, Integer> {

    static{
        register(ExprSensitivity.class, Integer.class,
                "sens[itivity]",
                "players");
    }

    @Override
    protected Integer @NonNull [] get(@NonNull Event event, Player @NonNull [] source) {
        if (VulcanAPI.Factory.getApi() != null) {
            return new Integer[]{VulcanAPI.Factory.getApi().getSensitivity(source[0])};
        }
        return new Integer[]{};
    }

    @Override
    public @NonNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "sensitivity of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
