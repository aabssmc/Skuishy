package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Entity - Eye Height")
@Description("Returns the eye height of the entity.")
@Examples({
        "send eye level of {_e}"
})
@Since("2.1")
public class ExprEyeHeight extends PropertyExpression<LivingEntity, Number> {

    static {
        register(ExprEyeHeight.class, Number.class,
                "eye (height|level)",
                "livingentity");
    }

    @Override
    protected Number @NotNull [] get(@NotNull Event e, LivingEntity[] source) {
        return new Number[]{source[0].getEyeHeight()};
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "eye height";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
