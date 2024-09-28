package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Ravager;

@Name("Ravager - Attack/Roar/Stun Ticks")
@Description("Gets/sets the attack, roar or stunned ticks of a ravager.")
@Examples({
        "set attack ticks of {_ravager} to 200"
})
@Since("2.8")
public class ExprRavagerTicks extends EntityExpression<Ravager, Integer> {

    static {
        register(ExprRavagerTicks.class, Integer.class, "[ravager] (:attack|stun[ned]|:roar) ticks", "entities");
    }

    @Override
    public Integer get(Ravager ravager) {
        return tags.contains("attack") ? ravager.getAttackTicks() : tags.contains("roar") ? ravager.getRoarTicks() : ravager.getAttackTicks();
    }

    @Override
    public void change(Ravager ravager, Integer aInteger, Changer.ChangeMode mode) {
        if (aInteger != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("attack")) {
                ravager.setAttackTicks(aInteger);
            } else if (tags.contains("roar")) {
                ravager.setRoarTicks(aInteger);
            } else {
                ravager.setStunnedTicks(aInteger);
            }
        }
    }
}
