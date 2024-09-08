package lol.aabss.skuishy.elements.vulcan.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.elements.vulcan.VulcanHook;
import me.frep.vulcan.api.VulcanAPI$Factory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Vulcan - Kurtosis of Player")
@Examples("send kurtosis of player")
@Description("Represents the kurtosis of a player.")
@Since("2.0")
public class ExprKurtosis extends PropertyExpression<Player, Number> {

    static{
        if (VulcanHook.vulcanEnabled()) {
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
                kurtosis.add(VulcanAPI$Factory.getApi().getKurtosis(p));
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "kurtosis of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
