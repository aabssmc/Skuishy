package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.SpectralArrow;
import org.jetbrains.annotations.Nullable;

@Name("Spectral Arrow - Glowing Ticks")
@Description("Gets/sets the glowing ticks of a spectral arrow.")
@Examples({
        "set glow ticks of {_spectralarrow} to 10"
})
@Since("2.8")
public class ExprSpectralArrowGlowingTicks extends EntityExpression<SpectralArrow, Integer> {

    static {
        register(ExprSpectralArrowGlowingTicks.class, Integer.class, "[spectral[ |-]arrow] glow[ing] ticks", "entities");
    }

    @Override
    public Integer get(SpectralArrow spectralArrow) {
        return spectralArrow.getGlowingTicks();
    }

    @Override
    public void change(SpectralArrow spectralArrow, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            spectralArrow.setGlowingTicks(integer);
        }
    }
}
