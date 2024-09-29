package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Vindicator;

@Name("Vindicator - Is Johnny")
@Description("True if the vindicator is a johnny.")
@Examples({
        "if {_vindicator} is johnny:",
        "\tset johnny state of {_vindicator} to false"
})
@Since("2.8")
public class CondIsVindicatorJohnny extends EntityCondition<Vindicator> {

    static {
        register(CondIsVindicatorJohnny.class, "[vindicator] johnny", "entities");
    }

    @Override
    protected boolean run(Vindicator vindicator) {
        return vindicator.isJohnny();
    }

}