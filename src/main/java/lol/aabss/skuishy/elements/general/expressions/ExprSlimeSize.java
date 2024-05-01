package lol.aabss.skuishy.elements.general.expressions;

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
import org.bukkit.entity.Slime;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Slime - Size")
@Description("Gets/Sets the size of a slime.")
@Examples({
        "set size of last spawned slime to 10"
})
@Since("2.1")
public class ExprSlimeSize extends PropertyExpression<Entity, Integer> {

    static {
        register(ExprSlimeSize.class, Integer.class,
                "slime size",
                "entities");
    }

    @Override
    protected Integer @NotNull [] get(@NotNull Event event, Entity @Nullable [] source) {
        if (source != null){
            List<Integer> size = new ArrayList<>();
            for (Entity slime : source){
                if (slime instanceof Slime) {
                    size.add(((Slime) slime).getSize());
                }
            }
            return size.toArray(Integer[]::new);
        }
        return new Integer[]{null};
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "size of slime";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && delta != null){
            for (Entity slime : getExpr().getArray(e)){
                if (slime instanceof Slime){
                    ((Slime) slime).setSize((Integer) delta[0]);
                }
            }
        }
    }

    @Override
    public @Nullable Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            return CollectionUtils.array(Integer.class);
        }
        return null;
    }
}
