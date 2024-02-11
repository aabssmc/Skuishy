package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
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
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
@Name("Block - Map Color")
@Description("Gets the map color from a block.")
@Examples({
        "if map color of block under player is green or lime:",
        "\tkill player",
        "\tsend \"no standing on green!\""
})
@Since("1.9")
public class ExprMapColor extends PropertyExpression<Block, Color> {

    static {
        if (Skript.methodExists(BlockData.class, "getMapColor")){
            register(ExprMapColor.class, Color.class,
                    "(map|exact) colo[u]r",
                    "blocks");
        }
    }

    @Override
    protected @Nullable Color[] get(@NotNull Event event, Block[] source) {
        List<SkriptColor> colors = new ArrayList<>();
        for (Block block : source) {
            colors.add(SkriptColor.fromBukkitColor(block.getBlockData().getMapColor()));
        } return colors.toArray(Color[]::new);
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
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }
}
