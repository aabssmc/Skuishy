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
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Arrow - Base Potion Type")
@Description("Gets/Sets the base potion type of an arrow.")
@Examples({
        "set base potion type to strong harming"
})
@Since("1.5")
public class ExprBaseEffect extends PropertyExpression<Entity, PotionType> {

    static {
        register(ExprBaseEffect.class, PotionType.class,
                "base (potion|effect) type",
                "potionitemtype");
    }

    @Override
    protected PotionType @NotNull [] get(@NotNull Event event, Entity @NotNull [] source) {
        if (source[0] instanceof Arrow arrow){
            return new PotionType[]{arrow.getBasePotionType()};
        }
        return new PotionType[0];
    }

    @Override
    public @NotNull Class<? extends PotionType> getReturnType() {
        return PotionType.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "base potion type";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(PotionType.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        Entity en = getExpr().getSingle(e);
        if (en instanceof Arrow) {
            if (mode == Changer.ChangeMode.SET) {
                ((Arrow) en).setBasePotionType((PotionType) delta[0]);
            }
        }
    }
}
