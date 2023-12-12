package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.DecoratedPot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Block - Decorated Pot Shred")
@Description("Gets/Sets the shred of a decorated pot side")
@Examples({
        "set pot shred side left of target block to player's tool"
})
@Since("1.9")
public class ExprPotShred extends SimpleExpression<Material> {

    static{
        Skript.registerExpression(ExprPotShred.class, Material.class,  ExpressionType.COMBINED,
                "[the] [decorated] pot shr(e|a)rd of [side] %potside% of %block%",
                "%block%[']s [decorated] pot shr(e|a)rd of [side] %potside%"
        );
    }

    private Expression<DecoratedPot.Side> side;
    private Expression<Block> block;

    @Override
    protected @Nullable Material[] get(@NotNull Event e) {
        Block block = this.block.getSingle(e);
        DecoratedPot.Side side = this.side.getSingle(e);
        if (block instanceof DecoratedPot){
            assert side != null;
            return new Material[]{((DecoratedPot) block).getSherd(side)};
        }
        return new Material[0];
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            Block block = this.block.getSingle(e);
            DecoratedPot.Side side = this.side.getSingle(e);
            if (block instanceof DecoratedPot){
                assert side != null;
                ((DecoratedPot) block).setSherd(side, (Material) delta[0]);
            }
        } else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Material> getReturnType() {
        return Material.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "pot sherd";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            side = (Expression<DecoratedPot.Side>) exprs[0];
            block = (Expression<Block>) exprs[1];
            return true;
        }
        block = (Expression<Block>) exprs[0];
        side = (Expression<DecoratedPot.Side>) exprs[1];
        return true;
    }
}
