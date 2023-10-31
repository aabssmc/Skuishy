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
import lol.aabss.skuishy.other.skins.PlayerTexture;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Name("Skins - Player Skin Texture (URL)")
@Description("sticking out your gyat for the rizzler, your so skibidi, your so fanum tax, i jus wana be ur sigma ):")
@Examples({
        "send texture of player as url"
})
@Since("1.0")

public class ExprPlayerTexURL extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlayerTexURL.class, String.class, ExpressionType.PROPERTY,
                "[the] [skin] texture of %player% as url",
                "%player%'s [skin] texture as url"
        );
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
        return "Player Skin Texture URL";
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event) {
        try {
            return new String[] {PlayerTexture.urlTexture(Objects.requireNonNull(player.getSingle(event)))};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


//sticking out your gyat for the rizzler, your so skibidi, your so fanum tax, i jus wana be ur sigma ):