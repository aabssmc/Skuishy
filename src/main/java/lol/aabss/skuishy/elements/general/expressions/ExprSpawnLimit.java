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
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NullableProblems")
@Name("World - Spawn Limit")
@Description("Gets/Sets a spawn limit of a spawn category in a world.")
@Examples({
        "set spawn limit of ambient to 0"
})
@Since("1.8")

public class ExprSpawnLimit extends SimpleExpression<Integer> {

    static{
        if (Skript.methodExists(World.class, "getSpawnLimit") && Skript.classExists("org.bukkit.entity.SpawnCategory")) {
            Skript.registerExpression(ExprSpawnLimit.class, Integer.class, ExpressionType.COMBINED,
                    "spawn[( |-)]limit of %spawncategory% in world %world%"
            );
        }
    }

    private Expression<SpawnCategory> spawncategory;
    private Expression<World> world;

    @Override
    protected @Nullable Integer[] get(@NotNull Event e) {
        World world = this.world.getSingle(e);
        SpawnCategory spawncategory = this.spawncategory.getSingle(e);
        if (world != null && spawncategory != null) {
            return new Integer[]{world.getSpawnLimit(spawncategory)};
        }
        return new Integer[]{null};
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET && delta != null) {
            World world = this.world.getSingle(e);
            SpawnCategory spawncategory = this.spawncategory.getSingle(e);
            if (world != null && spawncategory != null) {
                world.setSpawnLimit(spawncategory, (Integer) delta[0]);
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Integer.class);
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "spawn category";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        this.spawncategory = (Expression<SpawnCategory>) exprs[0];
        this.world = (Expression<World>) exprs[1];
        return true;
    }
}
