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
import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Fuel Ticks")
@Description("Gets/sets the fuel ticks of a powered minecart.")
@Examples({
        "set fuel ticks of {_minecart} to 20 # 1 second"
})
@Since("2.8")
public class ExprMinecartPush extends SimplePropertyExpression<Entity, Number> {

    static {
        register(ExprMinecartPush.class, Number.class, "[minecart] push (:x|z)", "entities");
    }

    private boolean x;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        x = parseResult.hasTag("x");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "fuel ticks";
    }

    @Override
    public @Nullable Number convert(Entity entity) {
        if (entity instanceof PoweredMinecart) {
            return x ? ((PoweredMinecart) entity).getPushX() : ((PoweredMinecart) entity).getPushZ();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Integer) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof PoweredMinecart) {
                        if (x) {
                            ((PoweredMinecart) entity).setPushX((Integer) delta[0]);
                        } else {
                            ((PoweredMinecart) entity).setPushZ((Integer) delta[0]);
                        }
                    }
                }
            }
        }
    }
}