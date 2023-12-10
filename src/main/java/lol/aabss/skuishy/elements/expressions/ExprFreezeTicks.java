package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Entity - Freeze Ticks")
@Description("Gets/Sets the freeze ticks of entities")
@Examples({
        "lock freeze ticks of player"
})
@Since("1.9")

public class ExprFreezeTicks extends PropertyExpression<Entity, Integer> {

    static {
        register(ExprFreezeTicks.class, Integer.class,
                "[:max] (freeze|frozen) ticks",
                "entities");
    }

    private Boolean max;

    @Override
    protected Integer @NotNull [] get(@NotNull Event e, Entity @NotNull [] source) {
        for (Entity entity : getExpr().getArray(e)){
            if (max){
                return new Integer[]{entity.getMaxFreezeTicks()};
            }
            return new Integer[]{entity.getFreezeTicks()};
        }
        return new Integer[0];
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            for (Entity entity : getExpr().getArray(e)){
                entity.setFreezeTicks((Integer) delta[0]);
            }
        } else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Integer.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "freeze ticks";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        max = parseResult.hasTag("max");
        return true;
    }
}
