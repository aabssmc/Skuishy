package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
@Name("Entity - Custom Name Visibility")
@Description("Gets/sets the custom name visibility of entities.")
@Examples({
        "set custom name visibility of target entity to true"
})
@Since("1.7.5")
public class ExprCustomNameVisibility extends PropertyExpression<Entity, Boolean> {

    static {
        register(ExprCustomNameVisibility.class, Boolean.class,
                "custom[ ]name visib(le|ility)",
                "entities"
        );
    }

    @Override
    protected @Nullable Boolean[] get(@NotNull Event e, Entity @NotNull [] source) {
        List<Boolean> entities = new ArrayList<>();
        for (Entity entity : getExpr().getArray(e))
            entities.add(entity.isCustomNameVisible());
        return entities.toArray(Boolean[]::new);
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET && delta != null) {
            Entity[] en = getExpr().getArray(e);
            for (Entity entity : en) {
                entity.setCustomNameVisible((Boolean) delta[0]);
            }
        }
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "custom name visibility";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }
}
