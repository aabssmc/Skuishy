package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Spectral Arrow - Glowing Ticks")
@Description("Gets/sets the glowing ticks of a spectral arrow.")
@Examples({
        "set glow ticks of {_spectralarrow} to 10"
})
@Since("2.8")
public class ExprSpectralArrowGlowingTicks extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprSpectralArrowGlowingTicks.class, Integer.class, "[spectral[ |-]arrow] glow[ing] ticks", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "glow ticks";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof SpectralArrow) {
            return ((SpectralArrow) entity).getGlowingTicks();
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
                    if (entity instanceof SpectralArrow) {
                        ((SpectralArrow) entity).setGlowingTicks((Integer) delta[0]);
                    }
                }
            }
        }
    }
}
