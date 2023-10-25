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
import lol.aabss.skuishy.other.PlayerTexture;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Player Skin Texture - URL")
@Description("Gets the player's skin as a url.")
@Examples({
        "send texture of player as url"
})
@Since("1.0")

public class ExprPlayerTexURL extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlayerTexURL.class, String.class, ExpressionType.COMBINED, "[the] [skin] texture of %player% as url", "%player%'s [skin] texture as url");
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
        return "Player Skin Texture URL";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        try {
            return new String[] {PlayerTexture.urlTexture(player.getSingle(event).getUniqueId())};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
