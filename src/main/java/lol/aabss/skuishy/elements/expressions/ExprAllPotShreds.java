package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.DecoratedPot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Block - Decorated Pot Shreds")
@Description("Gets all of the shreds of a decorated pot")
@Examples({
        "give player pot shreds of target block"
})
@Since("1.9")
public class ExprAllPotShreds extends SimpleExpression<Material> {

    static{
        Skript.registerExpression(ExprAllPotShreds.class, Material.class, ExpressionType.SIMPLE,
                "[all [[of] the]] pot shreds of %block%"
        );
    }

    private Expression<Block> block;

    @Override
    protected @Nullable Material[] get(@NotNull Event e) {
        if (block instanceof DecoratedPot){
            return ((DecoratedPot) block).getSherds().values().toArray(new Material[0]);
        }
        return new Material[0];
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Material> getReturnType() {
        return Material.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "all pot shreds of block";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init( Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        return true;
    }
}
