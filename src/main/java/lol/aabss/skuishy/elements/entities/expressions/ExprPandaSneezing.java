package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Panda;

@Name("Panda - Sneezing")
@Description("Gets/sets the sneezing state of a panda.")
@Examples({
        "set sneezing state of {_panda} to false"
})
@Since("2.8")
public class ExprPandaSneezing extends EntityExpression<Panda, Boolean> {

    static {
        register(ExprPandaSneezing.class, Boolean.class, "[panda] sneezing [state|mode]", "entities");
    }

    @Override
    public Boolean get(Panda panda) {
        return panda.isSneezing();
    }

    @Override
    public void change(Panda panda, Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            panda.setSneezing(aBoolean);
        }
    }
}
