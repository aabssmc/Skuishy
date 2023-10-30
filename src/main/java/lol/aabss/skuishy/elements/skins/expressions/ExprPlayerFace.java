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
import lol.aabss.skuishy.other.skins.PlayerFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.util.Objects;

@Name("Skins - Face of Player")
@Description("Gets the player's face.")
@Examples({
        "set {_texture} to face of player with an outer layer"
})
@Since("1.0")

public class ExprPlayerFace extends SimpleExpression<BufferedImage> {

    static {
        Skript.registerExpression(ExprPlayerFace.class, BufferedImage.class, ExpressionType.PROPERTY, "[the] face of %player% (at|with) size %number% with[:out] (a|an) [outer[( |-)]]layer", "%player%'s face (at|with) size %number% with[:out] (a|an) [outer[( |-)]]layer");
    }

    private Expression<Player> player;

    private Expression<Number> size;

    private boolean layer;

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
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        size = (Expression<Number>) exprs[1];
        layer = parser.hasTag("out");
        return true;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "Player Skin Face";
    }

    @Override
    protected BufferedImage @NotNull [] get(@NotNull Event event) {
        try {
            if (layer){
                return new BufferedImage[] {PlayerFace.get(Objects.requireNonNull(player.getSingle(event)), size.getSingle(event), true)};
            }
            else{
                return new BufferedImage[] {PlayerFace.get(Objects.requireNonNull(player.getSingle(event)), size.getSingle(event), false)};
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
