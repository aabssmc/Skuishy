package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprLlamaCaravan extends SimplePropertyExpression<Entity, Entity> {

    static {
        register(ExprLlamaCaravan.class, Entity.class, "llama caravan (:head|tail)", "entities");
    }

    private boolean head;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        head = parseResult.hasTag("head");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "llama caravan";
    }

    @Override
    public @Nullable Entity convert(Entity entity) {
        if (entity instanceof Llama) {
            return head ? ((Llama) entity).getCaravanHead() : ((Llama) entity).getCaravanTail();
        }
        return null;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return null;
    }
}
