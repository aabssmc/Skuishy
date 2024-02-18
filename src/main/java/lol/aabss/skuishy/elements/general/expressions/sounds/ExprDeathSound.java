package lol.aabss.skuishy.elements.general.expressions.sounds;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Entity - Death Sound")
@Description("Gets the death sound of the entity.")
@Examples({
        "send death sound of {_b}"
})
@Since("2.1")
public class ExprDeathSound extends PropertyExpression<LivingEntity, String> {

    static {
        if (Skript.methodExists(Mob.class, "getDeathSound")) {
            register(ExprDeathSound.class, String.class,
                    "[entity] death sound",
                    "livingentity");
        }
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, LivingEntity[] source) {
        List<String> sounds = new ArrayList<>();
        for (LivingEntity mob : source) {
            if (mob.getDeathSound() != null) {
                sounds.add(mob.getDeathSound().getKey().getKey());
            } else {
                sounds.add("none");
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
        return "death sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends LivingEntity>) exprs[0]);
        return true;
    }
}
