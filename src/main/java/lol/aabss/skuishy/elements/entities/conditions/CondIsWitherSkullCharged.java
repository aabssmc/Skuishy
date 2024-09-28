package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.WitherSkull;

@Name("Wither Skull - Is Charged")
@Description("True if the wither skull is charged.")
@Examples({
        "if {_witherskull} is charged:",
        "\tset charged state of {_witherskull} to false"
})
@Since("2.8")
public class CondIsWitherSkullCharged extends EntityCondition<WitherSkull> {

    static {
        register(CondIsWitherSkullCharged.class, "[wither[ ]skull] charged", "entities");
    }

    @Override
    protected boolean run(WitherSkull witherskull) {
        return witherskull.isCharged();
    }
}
