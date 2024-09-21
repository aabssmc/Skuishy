package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Hoglin;

@Name("Hoglin - Is Immune To Zombification")
@Description("Returns true if the hoglin is immune to zombification.")
@Examples({
        "if {_hoglin} is immune to zombification:",
        "\tbroadcast \"abc123abc123abc123\""
})
@Since("2.0")
public class CondIsHoglinImmuneToZombification extends EntityCondition<Hoglin> {

    static {
        register(CondIsHoglinImmuneToZombification.class, "[hoglin] immune to zombif(ying|ication)", "livingentities");
    }

    @Override
    protected boolean run(Hoglin hoglin) {
        return hoglin.isImmuneToZombification();
    }
}
