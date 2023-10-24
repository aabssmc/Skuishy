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
import lol.aabss.skuishy.other.PlayerSignature;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Player Skin Signature")
@Description("Sends the signature of the player's skin .")
@Examples({
        "command sendsignature <player>:",
        "\tsend arg-1's skin signature"
})
@Since("1.0")

public class ExprPlayerSig extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlayerSig.class, String.class, ExpressionType.COMBINED, "[the] [(texture|skin)] signature of %player%", "%player%'s [(texture|skin)] signature");
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
        return "Player Skin Signature ";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        try {
            return new String[] {PlayerSignature.Signature(player.getSingle(event).getUniqueId())};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
