package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Tnt/Minecart/Creeper - Fuse Ticks")
@Description("Gets/sets the fuse ticks of a primed tnt, explosive minecart or creeper.")
@Examples({
        "set fuse ticks of {_tnt} to 10"
})
@Since("2.8")
public class ExprTntFuseTicks extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprTntFuseTicks.class, Integer.class, "[([primed[-| ]] tnt|minecart)] [:max] fuse ticks", "entities");
    }

    private boolean max;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        max = parseResult.hasTag("max");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "fuse ticks";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof TNTPrimed) {
            return ((TNTPrimed) entity).getFuseTicks();
        } else if (entity instanceof ExplosiveMinecart) {
            return ((ExplosiveMinecart) entity).getFuseTicks();
        } else if (entity instanceof Creeper) {
            return max ? ((Creeper) entity).getMaxFuseTicks() : ((Creeper) entity).getFuseTicks();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Integer.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Integer) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof TNTPrimed) {
                        ((TNTPrimed) entity).setFuseTicks((Integer) delta[0]);
                    } else if (entity instanceof ExplosiveMinecart) {
                        ((ExplosiveMinecart) entity).setFuseTicks((Integer) delta[0]);
                    }
                }
            }
        }
    }
}
