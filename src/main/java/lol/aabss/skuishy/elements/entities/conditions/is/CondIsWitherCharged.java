package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Wither;

@Name("Wither - Is Charged")
@Description("Returns true if the wither is charged.")
@Examples({
        "if last spawned wither is charged:"
})
@Since("2.8")
public class CondIsWitherCharged extends EntityCondition<Wither> {

    static {
        register(CondIsWitherCharged.class, "[wither] charged", "entities");
    }

    @Override
    protected boolean run(Wither wither) {
        return wither.isCharged();
    }
}

