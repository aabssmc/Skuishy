package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
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
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Arrow - Base Potion Type")
@Description("Gets/Sets the base potion type of an arrow.")
@Examples({
        "set base potion type to strong harming"
})
@Since("2.1")
public class ExprBaseEffect extends PropertyExpression<Entity, PotionType> {

    static {
        if (Skript.methodExists(Arrow.class, "getBasePotionType")) {
            register(ExprBaseEffect.class, PotionType.class,
                    "base (potion|effect) type",
                    "entities");
        }
    }

    @Override
    protected PotionType @NotNull [] get(@NotNull Event event, Entity @NotNull [] source) {
        if (source instanceof Arrow[] arrow){
            List<PotionType> potions = new ArrayList<>();
            for (Arrow e : arrow) {
                potions.add(e.getBasePotionType());
            } return potions.toArray(PotionType[]::new);
        }
        return new PotionType[]{null};
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
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
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && delta != null) {
            Entity[] arrow = getExpr().getArray(e);
            for (Entity en : arrow) {
                if (en instanceof Arrow) {
                    ((Arrow) en).setBasePotionType((PotionType) delta[0]);
                }
            }
        }
    }
}
