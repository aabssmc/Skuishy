package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.*;
import org.jetbrains.annotations.Nullable;

@Name("Evoker Fangs - Attack Delay")
@Description("Gets/sets the attack delay of evoker fangs.")
@Examples({
        "set attack delay of {_evokerfangs} to 1"
})
@Since("2.8")
public class ExprEvokerFangsAttackDelay extends EntityExpression<EvokerFangs, Integer> {
    static {
        register(ExprEvokerFangsAttackDelay.class, Integer.class, "[evoker] fangs attack delay", "entities");
    }

    @Override
    public Integer get(EvokerFangs evokerFangs) {
        return evokerFangs.getAttackDelay();
    }

    @Override
    public void change(EvokerFangs evokerFangs, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && integer != null) {
            evokerFangs.setAttackDelay(integer);
        }
    }
}
