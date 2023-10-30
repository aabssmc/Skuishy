package lol.aabss.skuishy.elements.skins.expressions;

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
import lol.aabss.skuishy.other.skins.Property;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Skins - Player Skin Signature")
@Description("Sends the signature of the player's skin .")
@Examples({
        "command sendsignature <player>:",
        "\tsend arg-1's skin signature"
})
@Since("1.0")

public class ExprPlayerSig extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlayerSig.class, String.class, ExpressionType.PROPERTY, "[the] [(texture|skin)] signature of %player%", "%player%'s [(texture|skin)] signature");
    }

    private Expression<Player> player;

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "Player Skin Signature ";
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event) {
        Player p = player.getSingle(event);
        assert p != null;
        return new String[]{Property.jo(p).getSignature()};
    }
}
