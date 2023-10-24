package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.PlayerValue;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Player Skin Value")
@Description("Sends the value of the player's skin .")
@Examples({
        "command sendvalue <player>:",
        "\tsend arg-1's skin value"
})
@Since("1.0")

public class ExprPlayerVal extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlayerVal.class, String.class, ExpressionType.COMBINED, "[the] [(texture|skin)] value of %player%", "%player%'s [(texture|skin)] value");
    }

    private Expression<Player> player;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    @Nullable
    public String toString(Event event, boolean debug) {
        return "Player Skin Value ";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        try {
            return new String[] {PlayerValue.Value(player.getSingle(event).getUniqueId())};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
