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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Entity - Hurt Sound")
@Description("Gets the hurt sound of the entity.")
@Examples({
        "send hurt sound of {_b}"
})
@Since("2.1")
public class ExprHurtSound extends PropertyExpression<LivingEntity, String> {

    static {
        register(ExprHurtSound.class, String.class,
                "[entity] hurt sound",
                "livingentity");
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, LivingEntity[] source) {
        if (source[0].getHurtSound() != null) {
            return new String[]{source[0].getHurtSound().name().replaceAll("_", ".").toLowerCase()};
        }
        return new String[]{"none"};
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "hurt sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
