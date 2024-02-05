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
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Entity - Swim Sound")
@Description("Gets the swim sound of the entity.")
@Examples({
        "send swim sound of {_b}"
})
@Since("2.1")
public class ExprSwimSound extends PropertyExpression<Entity, String> {

    static {
        if (Skript.methodExists(Entity.class, "getSwimSound")) {
            register(ExprSwimSound.class, String.class,
                    "[entity] swim sound",
                    "entities");
        }
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Entity[] source) {
        List<String> sounds = new ArrayList<>();
        for (Entity block : source) {
            sounds.add(block.getSwimSound().getKey().getKey());
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
        return "swim sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }
}
