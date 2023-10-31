package lol.aabss.skuishy.elements.skins.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
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

public class ExprPlayerTexURL extends PropertyExpression<Player, String> {

    static {
        register(ExprPlayerTexURL.class, String.class,
                "[the] [skin] texture url",
                "players"
        );
    }

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
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return getExpr().toString(event, debug) + " Skin Texture URL";
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Player @NotNull [] source) {
        try {
            if (source.length < 1) return new String[0];
            var player = source[0];
            return new String[] {PlayerTexture.urlTexture(player)};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


//sticking out your gyat for the rizzler, your so skibidi, your so fanum tax, i jus wana be ur sigma ):