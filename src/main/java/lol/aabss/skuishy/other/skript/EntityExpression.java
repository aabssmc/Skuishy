package lol.aabss.skuishy.other.skript;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.google.common.reflect.TypeToken;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class EntityExpression<F extends Entity, T> extends SimplePropertyExpression<Entity, T> implements EntityStatement<F> {

    protected int matchedPattern;
    protected List<String> tags;
    protected Expression<?>[] exprs;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends F>) exprs[0]);
        tags = parseResult.tags;
        this.exprs = exprs;
        this.matchedPattern = matchedPattern;
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        String name = this.getClass().getSimpleName();
        name = name.replaceFirst("Expr", "");
        name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
        return name.toLowerCase();
    }

    @Override
    public @Nullable T convert(Entity entity) {
        if (accepts(entity)) {
            return get((F) entity);
        }
        return null;
    }

    public abstract T get(F f);

    public void change(F f, @Nullable T t, Changer.ChangeMode mode) {}

    public List<Changer.ChangeMode> acceptedChanges() {
        return List.of(Changer.ChangeMode.SET);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return super.toString(e, debug);
    }

    @Override
    public @NotNull Class<? extends T> getReturnType() {
        return (Class<? extends T>) new TypeToken<T>(getClass()){}.getRawType();
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (changeExists()) {
            if (acceptedChanges().contains(mode)) {
                return CollectionUtils.array(new TypeToken<T>(getClass()){}.getRawType());
            }
        }
        return null;
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (changeExists()){
            for (Entity f : getExpr().getArray(event)) {
                if (accepts(f)) change((F) f, delta.length >= 1 ? (T) delta[0] : null, mode);
            }
        }
    }
}
