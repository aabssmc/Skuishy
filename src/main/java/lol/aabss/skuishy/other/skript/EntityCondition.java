package lol.aabss.skuishy.other.skript;

import ch.njol.skript.conditions.base.PropertyCondition;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

// not rlly needed but condition didnt wanna feel left out
public abstract class EntityCondition<T extends Entity> extends PropertyCondition<Entity> implements EntityStatement<T> {

    protected abstract boolean run(T t);

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
