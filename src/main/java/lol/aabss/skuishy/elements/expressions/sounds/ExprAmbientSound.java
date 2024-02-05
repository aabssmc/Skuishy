package lol.aabss.skuishy.elements.expressions.sounds;

import ch.njol.skript.Skript;
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
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Entity - Ambient Sound")
@Description("Gets the ambient sound of the entity.")
@Examples({
        "send ambient sound of {_e}"
})
@Since("2.1")
public class ExprAmbientSound extends PropertyExpression<Entity, String> {

    static {
        if (Skript.methodExists(Mob.class, "getAmbientSound")) {
            register(ExprAmbientSound.class, String.class,
                    "[entity] ambient sound",
                    "entities");
        }
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Entity[] source) {
        List<String> sounds = new ArrayList<>();
        for (Entity mob : source) {
            if (mob instanceof Mob m) {
                if (m.getAmbientSound() != null) {
                    sounds.add(m.getAmbientSound().getKey().getKey());
                } else {
                    sounds.add("none");
                }
            }
        }
        return sounds.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
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
