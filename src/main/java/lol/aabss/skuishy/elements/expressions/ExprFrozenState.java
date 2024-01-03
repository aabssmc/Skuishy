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
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("TickManager - Frozen State")
@Description("Gets/sets the frozen mode of the server or an entity.")
@Examples({
        "set frozen state of server to true"
})
@Since("1.9")
public class ExprFrozenState extends SimpleExpression<Boolean> {

    static{
        Skript.registerExpression(ExprFrozenState.class, Boolean.class, ExpressionType.COMBINED,
                "[the] frozen [tick[s]] (state|mode) of ([the] server|%-entity%)",
                "([the] server|%-entity%)['s] frozen [tick[(s|[( |-)]rate)]] (state|mode)"
        );
    }

    private Expression<Entity> ent;

    @Override
    protected Boolean @NotNull [] get(@NotNull Event e) {
        Entity entity = ent.getSingle(e);
        if (entity != null){
            return new Boolean[]{entity.isFrozen()};
        }
        return new Boolean[]{Bukkit.getServer().getServerTickManager().isFrozen()};
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null) {
            if (mode == Changer.ChangeMode.SET) {
                Bukkit.getServer().getServerTickManager().setFrozen((Boolean) delta[0]);
            } else {
                assert false;
            }
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

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        ent = (Expression<Entity>) exprs[0];
        return true;
    }
}
