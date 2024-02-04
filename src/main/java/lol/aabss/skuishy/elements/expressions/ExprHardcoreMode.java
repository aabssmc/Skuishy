package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("World - Hardcore mode")
@Description("Gets/Sets the hardcore mode of a world.")
@Examples({
        "set spawn limit of ambient to 0"
})
@Since("1.7.5")

public class ExprHardcoreMode extends SimpleExpression<Boolean> {

    static{
        Skript.registerExpression(ExprHardcoreMode.class, Boolean.class, ExpressionType.SIMPLE,
                "hardcore [state|mode] (in|of) %world%",
                "%world%'s hardcore [state|mode]"
        );
    }

    private Expression<World> world;

    @Override
    protected @Nullable Boolean[] get(@NonNull Event e) {
        World world = this.world.getSingle(e);
        if (world != null) return new Boolean[]{world.isHardcore()};
        return new Boolean[]{};
    }

    @Override
    public void change(@NonNull Event e, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            World world = this.world.getSingle(e);
            if (world != null) {
                world.setHardcore((Boolean) delta[0]);
            }
        } else {
            assert false;
        }
    }

    @Override
    public Class<?> @NonNull [] acceptChange(final Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NonNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "hardcore mode";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        world = (Expression<World>) exprs[0];
        return true;
    }
}
