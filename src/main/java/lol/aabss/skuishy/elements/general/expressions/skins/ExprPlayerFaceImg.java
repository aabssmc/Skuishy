package lol.aabss.skuishy.elements.general.expressions.skins;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.skins.SkinWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Name("Skins - Face of Player as image")
@Description("Gets the player's face as image.")
@Examples({
        "command face-texture <player>:",
        "\ttrigger:",
        "\t\tset {_texture} to arg-1's face at size 8 with an outer layer as image"
})
@Since("1.0")
public class ExprPlayerFaceImg extends PropertyExpression<Player, BufferedImage> {

    static {
        register(ExprPlayerFaceImg.class, BufferedImage.class,
                "face [(at|with) size %-number%] (:with|:without) (a|an) [outer[( |-)]]layer as image",
                "players"
        );
    }

    private Expression<Number> size;
    private boolean without;

    @Override
    protected BufferedImage @NotNull [] get(@NotNull Event event, Player @NotNull [] source) {
        Number size = this.size != null ? this.size.getSingle(event) : 16;
        List<BufferedImage> faces = new ArrayList<>();
        for (Player p : source) {
            faces.add(SkinWrapper.get(p, size, !without));
        } return faces.toArray(BufferedImage[]::new);
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public @NotNull Class<? extends BufferedImage> getReturnType() {
        return BufferedImage.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "face of player as image";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        if (matchedPattern == 1) {
            setExpr((Expression<? extends Player>) exprs[0]);
            this.size = (Expression<Number>) exprs[1];
        } else if (matchedPattern == 0) {
            setExpr((Expression<? extends Player>) exprs[1]);
            this.size = (Expression<Number>) exprs[0];
        }
        this.without = parseResult.hasTag("without");
        return LiteralUtils.canInitSafely(this.size);
    }
}
