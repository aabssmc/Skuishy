package lol.aabss.skuishy.elements.expressions.sounds;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Entity - Swim Sound")
@Description("Gets the swim sound of the entity.")
@Examples({
        "send swim sound of {_b}"
})
@Since("2.1")
public class ExprSwimSound extends PropertyExpression<Entity, String> {

    static {
        register(ExprSwimSound.class, String.class,
                "[entity] swim sound",
                "entity");
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Entity[] source) {
        return new String[]{source[0].getSwimSound().name().replaceAll("_", ".").toLowerCase()};
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "swim sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
