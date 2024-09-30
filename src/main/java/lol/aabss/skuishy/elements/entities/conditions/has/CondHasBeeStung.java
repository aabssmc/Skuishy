package lol.aabss.skuishy.elements.entities.conditions.has;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Bee;

@Name("Bee - Has Strung")
@Description("Returns true if the bee has stung.")
@Examples({
        "if target entity has stung:"
})
@Since("2.0")
public class CondHasBeeStung extends EntityCondition<Bee> {

    static{
        register(CondHasBeeStung.class, PropertyType.HAVE, "stung", "livingentities");
    }

    @Override
    protected boolean run(Bee bee) {
        return bee.hasStung();
    }
}
