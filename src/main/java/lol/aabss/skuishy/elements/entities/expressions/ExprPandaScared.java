package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Panda;

@Name("Panda - Scared")
@Description("Gets the scared state of a panda.")
@Examples({
        "set scared state of {_panda} to false"
})
@Since("2.8")
public class ExprPandaScared extends EntityExpression<Panda, Boolean> {

    static {
        register(ExprPandaScared.class, Boolean.class, "[panda] scared [state|mode]", "entities");
    }

    @Override
    public Boolean get(Panda panda) {
        return panda.isScared();
    }
}
