package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Guardian;
import org.jetbrains.annotations.Nullable;

@Name("Guardian - Laser Ticks")
@Description("Gets/sets the laser ticks or duration of a guardian.")
@Examples({
        "set laser ticks of {_guardian} to 1"
})
@Since("2.8")
public class ExprGuardianLaserTicks extends EntityExpression<Guardian, Integer> {

    static {
        register(ExprGuardianLaserTicks.class, Integer.class, "[guardian] laser (ticks|:duration)", "entities");
    }

    @Override
    public Integer get(Guardian guardian) {
        return tags.contains("duration") ? guardian.getLaserDuration() : guardian.getLaserTicks();
    }

    @Override
    public void change(Guardian guardian, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            guardian.setLaserTicks(integer);
        }
    }
}