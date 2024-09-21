package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Hoglin;

@Name("Hoglin - Is Able To Be Hunted")
@Description("Returns true if the hoglin is able to be hunted.")
@Examples({
        "if last spawned hoglin is able to be hunted:"
})
@Since("2.0")
public class CondIsHoglinAbleToBeHunted extends EntityCondition<Hoglin> {

    static {
        register(CondIsHoglinAbleToBeHunted.class, "[hoglin] able to be hunted", "livingentities");
    }

    @Override
    protected boolean run(Hoglin hoglin) {
        return hoglin.isAbleToBeHunted();
    }
}
