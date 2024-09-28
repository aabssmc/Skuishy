package lol.aabss.skuishy.other.skript;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// not rlly needed but condition didnt wanna feel left out
public abstract class EntityCondition<T extends Entity> extends PropertyCondition<Entity> implements EntityStatement<T> {

    protected List<String> tags;

    protected abstract boolean run(T t);

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.tags = parseResult.tags;
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public boolean check(Entity entity) {
        if (accepts(entity)) {
            return run((T) entity);
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        String name = this.getClass().getSimpleName();
        name = name.replaceFirst("Cond", "");
        name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
        return name.toLowerCase();
    }
}
