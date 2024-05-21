package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

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
public class ExprMapColor extends PropertyExpression<Object, Color> {

    static {
        if (Skript.methodExists(BlockData.class, "getMapColor")){
            register(ExprMapColor.class, Color.class,
                    "(map|exact) colo[u]r",
                    "blocks/itemtypes/blockdatas/itemstacks");
        }
    }

    @Override
    protected @Nullable Color[] get(@NotNull Event event, Object[] source) {
        List<SkriptColor> colors = new ArrayList<>();
        for (Object obj : source) {
            if (obj instanceof Block block) {
                colors.add(SkriptColor.fromBukkitColor(block.getBlockData().getMapColor()));
            } else if (obj instanceof ItemType item){
                colors.add(SkriptColor.fromBukkitColor(item.getMaterial().createBlockData().getMapColor()));
            } else if (obj instanceof BlockData data){
                colors.add(SkriptColor.fromBukkitColor(data.getMapColor()));
            } else if (obj instanceof ItemStack item){
                colors.add(SkriptColor.fromBukkitColor(item.getType().createBlockData().getMapColor()));
            }
        } return colors.toArray(Color[]::new);
    }

    @Override
    public @NotNull Class<? extends Color> getReturnType() {
        return SkriptColor.BLACK.getClass();
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "block color from map";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr(exprs[0]);
        return true;
    }
}
