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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
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
    protected Number @NotNull [] get(@NotNull Event event, Player @NotNull [] source) {
        if (VulcanAPI.Factory.getApi() != null) {
            return new Number[]{VulcanAPI.Factory.getApi().getCps(source[0])};
        }
        return new Number[]{};
    }
    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "cps of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
