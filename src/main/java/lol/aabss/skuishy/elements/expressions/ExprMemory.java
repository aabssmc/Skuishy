package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ExprMemory extends SimpleExpression<Object> {

    static{
        Skript.registerExpression(ExprMemory.class, Object.class, ExpressionType.COMBINED,
                "memory[[ ]key] %memorykeys% of %livingentitys%",
                "%livingentitys%'s memory[[ ]key] %memorykeys%"
        );
    }

    private Expression<LivingEntity> entity;
    private Expression<MemoryKey<Object>> memorykey;

    @Override
    protected @Nullable Object[] get(@NotNull Event e) {
        LivingEntity[] ent = entity.getArray(e);
        MemoryKey<Object>[] mem = memorykey.getArray(e);
        for (LivingEntity livingEntity : ent){
            for (MemoryKey<Object> memoryKey : mem){
                return new Object[]{livingEntity.getMemory(memoryKey)};
            }
        }
        return new Object[0];
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            LivingEntity[] ent = entity.getArray(e);
            MemoryKey<Object>[] mem = memorykey.getArray(e);
            for (LivingEntity livingEntity : ent){
                for (MemoryKey<Object> memoryKey : mem){
                    livingEntity.setMemory(memoryKey, delta[0]);
                }
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
    public @NotNull Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "memory of entity";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            memorykey = (Expression<MemoryKey<Object>>) exprs[0];
            entity = (Expression<LivingEntity>) exprs[1];
        }
        else {
            entity = (Expression<LivingEntity>) exprs[0];
            memorykey = (Expression<MemoryKey<Object>>) exprs[1];
        }
        return true;
    }
}
