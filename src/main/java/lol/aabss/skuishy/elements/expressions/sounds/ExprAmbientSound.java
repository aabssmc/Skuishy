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
import org.bukkit.entity.Mob;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Entity - Ambient Sound")
@Description("Gets the ambient sound of the entity.")
@Examples({
        "send ambient sound of {_e}"
})
@Since("2.1")
public class ExprAmbientSound extends PropertyExpression<Entity, String> {

    static {
        register(ExprAmbientSound.class, String.class,
                "[entity] ambient sound",
                "entity");
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Entity[] source) {
        if (source[0] instanceof Mob m){
            if (m.getAmbientSound() != null) {
                return new String[]{m.getAmbientSound().name().replaceAll("_", ".").toLowerCase()};
            }
            return new String[]{"none"};
        }
        return new String[0];
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "ambient sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }
}
