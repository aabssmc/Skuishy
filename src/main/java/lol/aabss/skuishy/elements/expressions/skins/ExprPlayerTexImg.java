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
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

@Name("Skins - Player Skin Texture (Image)")
@Description("Gets the player's skin as a image.")
@Examples({
        "command set-texture <player>:",
        "\ttrigger:",
        "\t\tset {_texture} to skin texture image of player"
})
@Since("1.0")

public class ExprPlayerTexImg extends PropertyExpression<Player, BufferedImage> {

    static {
        register(ExprPlayerTexImg.class, BufferedImage.class,
                "[the] [skin] texture image",
                "players");
    }

    @Override
    public @NotNull Class<? extends BufferedImage> getReturnType() {
        return BufferedImage.class;
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
        return getExpr().toString(event, debug ) + " Skin Texture Image";
    }

    @Override
    protected BufferedImage @NotNull [] get(@NotNull Event event, Player @NotNull [] source) {
        try {
            if (source.length < 1) return new BufferedImage[0];
            var player = source[0];
            return new BufferedImage[] {SkinWrapper.imgTexture(player)};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
