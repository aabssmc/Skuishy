package lol.aabss.skuishy.elements.expressions.sounds;

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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Entity - Death Sound")
@Description("Gets the death sound of the entity.")
@Examples({
        "send death sound of {_b}"
})
@Since("2.1")
public class ExprDeathSound extends PropertyExpression<LivingEntity, String> {

    static {
        register(ExprDeathSound.class, String.class,
                "[entity] death sound",
                "livingentity");
    }

    @Override
    protected String @NonNull [] get(@NonNull Event event, LivingEntity[] source) {
        if (source[0].getDeathSound() != null) {
            return new String[]{source[0].getDeathSound().name().replaceAll("_", ".").toLowerCase()};
        }
        return new String[]{"none"};
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "death sound";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends LivingEntity>) exprs[0]);
        return true;
    }
}
