package lol.aabss.skuishy.elements.general.expressions;

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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
                "hardcore [state|mode] (in|of) %worlds%",
                "%worlds%'s hardcore [state|mode]"
        );
    }

    private Expression<World> world;

    @Override
    protected @Nullable Boolean[] get(@NotNull Event event) {
        List<Boolean> modes = new ArrayList<>();
        for (World world : this.world.getArray(event)){
            modes.add(world.isHardcore());
        }
        return modes.toArray(Boolean[]::new);
    }

    @Override
    public void change(@NotNull Event event, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET && delta != null) {
            World world = this.world.getSingle(event);
            if (world != null) {
                world.setHardcore((Boolean) delta[0]);
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return world.isSingle();
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "hardcore mode";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        world = (Expression<World>) exprs[0];
        return true;
    }
}
