package lol.aabss.skuishy.elements.tickmanager.expressions;

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
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
@Name("TickManager - Frozen State")
@Description("Gets/sets the frozen mode of the server or an entity.")
@Examples({
        "set frozen state of server to true"
})
@Since("1.9")
public class ExprFrozenState extends SimpleExpression<Boolean> {

    static{
        if (Skript.classExists("org.bukkit.ServerTickManager")) {
            Skript.registerExpression(ExprFrozenState.class, Boolean.class, ExpressionType.COMBINED,
                    "[the] frozen [tick[s]] (state|mode) of ([the] server|%-entities%)",
                    "([the] server|%-entities%)['s] frozen [tick[(s|[( |-)]rate)]] (state|mode)"
            );
        }
    }

    private Expression<Entity> ent;

    @Override
    protected @Nullable Boolean[] get(@NotNull Event e) {
        if (ent != null){
            List<Boolean> frozen = new ArrayList<>();
            for (Entity en : ent.getArray(e)){
                frozen.add(en.isFrozen());
            }
            return frozen.toArray(Boolean[]::new);
        }
        return new Boolean[]{Bukkit.getServer().getServerTickManager().isFrozen()};
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null && mode == Changer.ChangeMode.SET) {
            Bukkit.getServer().getServerTickManager().setFrozen((Boolean) delta[0]);
        }

    }

    @Override
    public boolean isSingle() {
        if (ent != null) {
            return ent.isSingle();
        } return true;
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
