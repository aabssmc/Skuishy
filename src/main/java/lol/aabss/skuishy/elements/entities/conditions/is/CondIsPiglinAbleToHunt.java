package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Piglin;

@Name("Piglin - Is Able To Be Hunted")
@Description("Returns true if the piglin is able to hunt.")
@Examples({
        "if last spawned piglin is able to hunt:"
})
@Since("2.0")
public class CondIsPiglinAbleToHunt extends EntityCondition<Piglin> {

    static {
        register(CondIsPiglinAbleToHunt.class, "[piglin] able to hunt", "livingentities");
    }

    @Override
    protected boolean run(Piglin piglin) {
        return piglin.isAbleToHunt();
    }
}
