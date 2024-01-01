package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.SculkCatalyst;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ExprBloom extends PropertyExpression<Block, Boolean> {

    static{
        register(ExprBloom.class, Boolean.class,
                "bloom (state|mode)",
                "blocks");
    }

    @Override
    protected Boolean @NotNull [] get(@NotNull Event event, Block @NotNull [] source) {
        if (source[0].getBlockData() instanceof SculkCatalyst b){
            return new Boolean[]{b.isBloom()};
        }
        return new Boolean[0];
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "bloom state";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            if (getExpr().getSingle(e).getBlockData() instanceof SculkCatalyst b){
                b.setBloom((Boolean) delta[0]);
            }
        }
        else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }
}
