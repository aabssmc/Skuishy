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
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
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
    protected @Nullable Boolean[] get(@NonNull Event e) {
        Entity entity = ent.getSingle(e);
        if (entity != null){
            return new Boolean[]{entity.isFrozen()};
        }
        return new Boolean[]{Bukkit.getServer().getServerTickManager().isFrozen()};
    }

    @Override
    public Class<?> @NonNull [] acceptChange(Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NonNull Event e, @Nullable Object[] delta, Changer.@NonNull ChangeMode mode) {
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
    public @NonNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "frozen state";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        ent = (Expression<Entity>) exprs[0];
        return true;
    }
}
