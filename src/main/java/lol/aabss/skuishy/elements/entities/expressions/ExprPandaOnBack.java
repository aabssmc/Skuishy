package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Panda;

@Name("Panda - On Back")
@Description("Gets/sets the on back state of a panda.")
@Examples({
        "set on back state of {_panda} to false"
})
@Since("2.8")
public class ExprPandaOnBack extends EntityExpression<Panda, Boolean> {

    static {
        register(ExprPandaOnBack.class, Boolean.class, "[panda] on back [state|mode]", "entities");
    }

    @Override
    public Boolean get(Panda panda) {
        return panda.isOnBack();
    }

    @Override
    public void change(Panda panda, Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            panda.setOnBack(aBoolean);
        }
    }
}
