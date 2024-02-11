package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.SculkCatalyst;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class ExprBloom extends PropertyExpression<Block, Boolean> {

    static{
        if (Skript.classExists("org.bukkit.block.data.type.SculkCatalyst")){
            register(ExprBloom.class, Boolean.class,
                    "bloom (state|mode)",
                    "blocks");
        }
    }

    @Override
    protected @Nullable Boolean[] get(@NotNull Event event, Block @Nullable [] source) {
        if (source != null) {
            List<Boolean> bloom = new ArrayList<>();
            for (Block b : source) {
                if (b.getBlockData() instanceof SculkCatalyst block) {
                    bloom.add(block.isBloom());
                }
            }
            return bloom.toArray(Boolean[]::new);
        }
        return new Boolean[]{null};
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
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
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET && delta != null) {
            for (Block b : getExpr().getArray(e)){
                if (b.getBlockData() instanceof SculkCatalyst block){
                    block.setBloom((Boolean) delta[0]);
                }
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }
}
