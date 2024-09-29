package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Strider;

@Name("Strider - Is Shivering")
@Description("True if the strider is shivering.")
@Examples({
        "if {_strider} is shivering:",
        "\tset shivering state of {_strider} to false"
})
@Since("2.8")
public class CondIsStriderShivering extends EntityCondition<Strider> {

    static {
        register(CondIsStriderShivering.class, "[strider] shivering", "entities");
    }

    @Override
    protected boolean run(Strider strider) {
        return strider.isShivering();
    }
}
