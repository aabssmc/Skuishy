package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.GlowSquid;
import org.jetbrains.annotations.Nullable;

@Name("Glow Squid - Dark Ticks Remaining")
@Description("Gets/sets the glow squid dark ticks.")
@Examples({
        "set dark ticks of {_glowsquid} to 1000"
})
@Since("2.8")
public class ExprGlowSquidDarkTicksRemaining extends EntityExpression<GlowSquid, Integer> {

    static {
        register(ExprGlowSquidDarkTicksRemaining.class, Integer.class, "[glow[ ]squid] dark ticks [remaining]", "entities");
    }

    @Override
    public Integer get(GlowSquid glowSquid) {
        return glowSquid.getDarkTicksRemaining();
    }

    @Override
    public void change(GlowSquid glowSquid, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            glowSquid.setDarkTicksRemaining(integer);
        }
    }
}