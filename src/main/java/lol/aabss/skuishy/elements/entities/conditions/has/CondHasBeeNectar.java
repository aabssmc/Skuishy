package lol.aabss.skuishy.elements.entities.conditions.has;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Bee;

@Name("Bee - Has Nectar")
@Description("Returns true if the bee has nectar.")
@Examples({
        "if target entity has nectar:"
})
@Since("2.0")
public class CondHasBeeNectar extends EntityCondition<Bee> {

    static {
        register(CondHasBeeNectar.class, PropertyType.HAVE, "nectar", "livingentities");
    }

    @Override
    protected boolean run(Bee bee) {
        return bee.hasNectar();
    }

}
