package lol.aabss.skuishy.elements.expressions.skins;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.skins.SkinWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

@Name("Skins - Player Skin Value")
@Description("Sends the value of the player's skin .")
@Examples({
        "command send-value <player>:",
        "\ttrigger:",
        "\t\tsend arg-1's skin value"
})
@Since("1.0")

public class ExprPlayerVal extends PropertyExpression<Player, String> {

    static {
       register(ExprPlayerVal.class, String.class,
                "(texture|skin) value",
                "players"
        );
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, @NonNull ParseResult parser) {
        setExpr ((Expression<Player>) exprs[0]);
        return true;
    }

    @Override
    public @NonNull String toString(Event event, boolean debug) {
        return getExpr().toString(event, debug) + " Skin Value ";
    }

    @Override
    protected String @NonNull [] get(@NonNull Event event, Player[] source) {
        if (source.length < 1) return new String[0];
        Player p = source[0];
        assert p != null;
        return new String[]{SkinWrapper.getProfileProperties(p).getValue()};
    }

}
