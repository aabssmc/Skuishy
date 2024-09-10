package lol.aabss.skuishy.elements.entities.expressions;

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
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Arrow - Custom Effects")
@Description("Gets/Sets/Removes the custom potion effects of an arrow.")
@Examples({
        "clear effects of last shot arrow"
})
@Since("2.1")
public class ExprCustomEffects extends PropertyExpression<Entity, PotionEffect> {

    static {
        register(ExprCustomEffects.class, PotionEffect.class, "[custom] [potion] effects", "entity");
    }

    @Override
    protected PotionEffect @NotNull [] get(@NotNull Event event, Entity @NotNull [] source) {
        if (source[0] instanceof Arrow) {
            return ((Arrow) source[0]).getCustomEffects().toArray(PotionEffect[]::new);
        }
        return new PotionEffect[]{null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends PotionEffect> getReturnType() {
        return PotionEffect.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "custom effects";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(PotionEffect.class);
        } else if (mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.REMOVE_ALL){
            return CollectionUtils.array();
        }
        return null;
    }

    @Override
    public void change(@NotNull Event event, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        Entity en = getExpr().getSingle(event);
        if (en instanceof Arrow) {
            if (mode == Changer.ChangeMode.ADD && delta != null) {
                ((Arrow) en).addCustomEffect((PotionEffect) delta[0], true);
            }
            else if (mode == Changer.ChangeMode.REMOVE) {
                ((Arrow) en).removeCustomEffect(((PotionEffect) delta[0]).getType());
            }
            else if (mode == Changer.ChangeMode.REMOVE_ALL) {
                ((Arrow) en).clearCustomEffects();
            }
        }
    }
}
