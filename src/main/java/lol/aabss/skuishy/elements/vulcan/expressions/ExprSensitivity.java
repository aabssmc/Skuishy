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

@Name("Vulcan - Sensitivity of Player")
@Description("Represents the sensitivity of a player.")
@Examples({
        "send the sensitivity of player"
})
@Since("2.0")
public class ExprSensitivity extends PropertyExpression<Player, Integer> {

    static{
        if (VulcanHook.vulcanEnabled()) {
            register(ExprSensitivity.class, Integer.class,
                    "sens[itivity]",
                    "players");
        }
    }

    @Override
    protected Integer @NotNull [] get(@NotNull Event event, Player @Nullable [] source) {
        if (source != null) {
            List<Integer> sensitivity = new ArrayList<>();
            for (Player p : source) {
                sensitivity.add(VulcanAPI$Factory.getApi().getSensitivity(p));
            }
            return sensitivity.toArray(Integer[]::new);
        }
        return new Integer[]{null};
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "sensitivity of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
