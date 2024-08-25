package lol.aabss.skuishy.other.skript;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.common.reflect.TypeToken;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class SimpleEntityEffect<T extends Entity> extends Effect {

    private Expression<Entity> entities;
    protected int matchedPattern;
    protected List<String> tags;

    abstract protected void execute(T t);

    @Override
    protected void execute(@NotNull Event event) {
        if (entities == null) {
            return;
        }
        for (Entity entity : entities.getArray(event)) {
            if (accepts(entity)) {
                execute((T) entity);
            }
        }
    }

    private boolean accepts(Entity entity) {
        return new TypeToken<T>(getClass()){}.getRawType().isAssignableFrom(entity.getClass());
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        tags = parseResult.tags;
        this.matchedPattern = matchedPattern;
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        String name = this.getClass().getSimpleName();
        name = name.replaceFirst("Eff", "");
        name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
        return name.toLowerCase()+" effect";
    }

}
