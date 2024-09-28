package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Panda;

@Name("Panda - Eating/Unhappy/Sneeze Ticks")
@Description("Gets/sets the eating, unhappy or sneeze ticks of a panda.")
@Examples({
        "set eating ticks of {_panda} to 200"
})
@Since("2.8")
public class ExprPandaTicks extends EntityExpression<Panda, Integer> {

    static {
        register(ExprPandaTicks.class, Integer.class, "[panda] (:eating|sneez(e|ing)|:unhappy) ticks", "entities");
    }

    @Override
    public Integer get(Panda panda) {
        return tags.contains("eating") ? panda.getEatingTicks() : tags.contains("unhappy") ? panda.getUnhappyTicks() : panda.getSneezeTicks();
    }

    @Override
    public void change(Panda panda, Integer aInteger, Changer.ChangeMode mode) {
        if (aInteger != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("eating")) {
                panda.setEatingTicks(aInteger);
            } else if (tags.contains("unhappy")) {
                panda.setUnhappyTicks(aInteger);
            } else {
                panda.setSneezeTicks(aInteger);
            }
        }
    }
}
