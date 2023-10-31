package lol.aabss.skuishy.elements.skins.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.skins.PlayerFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

@Name("Skins - Face of Player")
@Description("Gets the player's face.")
@Examples({
        "set {_texture} to face of player with an outer layer",
        "set {_texture} to player's face with size 10 with a layer"
})
@Since("1.0")


class ExprPlayerFace extends PropertyExpression<Player, BufferedImage> {

    static {
        register(ExprPlayerFace.class, BufferedImage.class,
                "face [(at|with)] size %-number%] (:with|:without) (a|an) [outer[( |-)]]layer",
                "players"
        );
    }

    private Expression<Number> size;
    private boolean without;

    @Override
    protected BufferedImage @NotNull [] get(@NotNull Event event, Player[] source) {
        Player player = source[0] != null ? source[0] : null;
        Number size = null;
        if (this.size != null) size = this.size.getSingle(event);
        if (player == null || size == null) return new BufferedImage[0];
        try {
            var buffer = PlayerFace.get(player, size, !without);
            return new BufferedImage[]{buffer};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Class<? extends BufferedImage> getReturnType() {
        return BufferedImage.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        if (this.size != null) {
            return Classes.getDebugMessage(getExpr()) + "'face with size " + this.size.toString(event, debug) +
                    (without ? "without" : "with") + " an layer";
        }
        return Classes.getDebugMessage(getExpr()) + "'face " +
                (without ? "without" : "with") + " an layer";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        this.size = LiteralUtils.defendExpression(exprs[1]);
        this.without = parseResult.hasTag("without");
        return LiteralUtils.canInitSafely(this.size);
    }
}
