package lol.aabss.skuishy.elements.expressions;

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
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
public class ExprBloom extends PropertyExpression<Block, Boolean> {

    static{
        if (Skript.methodExists(SculkCatalyst.class, "isBloom") && Skript.methodExists(SculkCatalyst.class, "setBloom", Boolean.class)){
            register(ExprBloom.class, Boolean.class,
                    "bloom (state|mode)",
                    "blocks");
        }
    }

    @Override
    protected @Nullable Boolean[] get(@NonNull Event event, Block @NonNull [] source) {
        if (source[0].getBlockData() instanceof SculkCatalyst b){
            return new Boolean[]{b.isBloom()};
        }
        return new Boolean[0];
    }

    @Override
    public @NonNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "bloom state";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }

    @Override
    public void change(@NonNull Event e, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode){
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
    public Class<?> @NonNull [] acceptChange(final Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }
}
