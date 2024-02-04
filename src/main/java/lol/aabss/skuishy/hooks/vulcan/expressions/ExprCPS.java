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
@Name("Vulcan - CPS of Player")
@Description("Represents the clicks per second of a player.")
@Examples({
        "send the cps of player"
})
@Since("2.0")
public class ExprCPS extends PropertyExpression<Player, Number> {

    static{
        register(ExprCPS.class, Number.class,
                "(cps|clicks[( |-)]per[( |-)]sec[ond])",
                "players");
    }

    @Override
    protected Number @NonNull [] get(@NonNull Event event, Player @NonNull [] source) {
        if (VulcanAPI.Factory.getApi() != null) {
            return new Number[]{VulcanAPI.Factory.getApi().getCps(source[0])};
        }
        return new Number[]{};
    }
    @Override
    public @NonNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "cps of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
