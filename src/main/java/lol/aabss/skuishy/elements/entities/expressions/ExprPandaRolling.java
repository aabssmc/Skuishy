package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Panda;

@Name("Panda - Rolling")
@Description("Gets/sets the rolling state of a panda.")
@Examples({
        "set rolling state of {_panda} to false"
})
@Since("2.8")
public class ExprPandaRolling extends EntityExpression<Panda, Boolean> {

    static {
        register(ExprPandaRolling.class, Boolean.class, "[panda] rolling [state|mode]", "entities");
    }

    @Override
    public Boolean get(Panda panda) {
        return panda.isRolling();
    }

    @Override
    public void change(Panda panda, Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            panda.setRolling(aBoolean);
        }
    }
}
