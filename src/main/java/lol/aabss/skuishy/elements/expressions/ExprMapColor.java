package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Block - Map Color")
@Description("Gets the map color from a block.")
@Examples({
        "if map color of block under player is green or lime:",
        "\tkill player",
        "\tsend \"no standing on green!\""
})
@Since("1.9")
public class ExprMapColor extends PropertyExpression<BlockData, Color> {

    static {
        register(ExprMapColor.class, Color.class,
                "map colo[u]r",
                "blockdata");
    }

    @Override
    protected Color @NotNull [] get(@NotNull Event event, BlockData[] source) {
        return new SkriptColor[]{SkriptColor.fromBukkitColor(source[0].getMapColor())};
    }

    @Override
    public @NotNull Class<? extends Color> getReturnType() {
        return Color.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "block color from map";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
