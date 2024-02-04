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
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Arrow - Custom Effects")
@Description("Gets/Sets/Removes the custom potion effects of an arrow.")
@Examples({
        "clear effects of last shot arrow"
})
@Since("2.1")
public class ExprCustomEffects extends PropertyExpression<Entity, PotionEffect> {

    static {
        register(ExprCustomEffects.class, PotionEffect.class,
                "[custom] [potion] effects",
                "entity"
        );
    }

    @Override
    protected PotionEffect @NonNull [] get(@NonNull Event event, Entity @NonNull [] source) {
        if (source[0] instanceof Arrow){
            return ((Arrow) source[0]).getCustomEffects().toArray(PotionEffect[]::new);
        }
        return new PotionEffect[0];
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NonNull Class<? extends PotionEffect> getReturnType() {
        return PotionEffect.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "custom effects";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }

    @Override
    public Class<?> @NonNull [] acceptChange(Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.REMOVE_ALL) {
            return CollectionUtils.array(PotionEffect.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NonNull Event e, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode) {
        Entity en = getExpr().getSingle(e);
        if (en instanceof Arrow) {
            if (mode == Changer.ChangeMode.ADD) {
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
