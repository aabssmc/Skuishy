package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ExprFrozenState extends SimpleExpression<Boolean> {

    static{
        Skript.registerExpression(ExprFrozenState.class, Boolean.class, ExpressionType.COMBINED,
                "[the] frozen [tick[s]] (state|mode) of ([the] server|%entity%)",
                "([the] server|%entity%)['s] frozen [tick[(s|[( |-)]rate)]] (state|mode)"
        );
    }

    private Expression<Entity> ent;

    @Override
    protected @Nullable Boolean[] get(@NotNull Event e) {
        if (ent != null){
            return new Boolean[]{ent.getSingle(e).isFrozen()};
        }
        return new Boolean[]{Bukkit.getServerTickManager().isFrozen()};
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            Bukkit.getServerTickManager().setFrozen((Boolean) delta[0]);
        }
        else{
            assert false;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "frozen state";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        ent = (Expression<Entity>) exprs[0];
        return true;
    }
}
