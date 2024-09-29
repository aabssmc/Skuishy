package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Trident;

@Name("Trident - Has Glint")
@Description("Returns true if the trident has glint.")
@Examples({
        "if target entity has glint:"
})
@Since("2.0")
public class CondHasTridentGlint extends EntityCondition<Trident> {

    static {
        register(CondHasTridentGlint.class, PropertyType.HAVE, "glint", "entities");
    }

    @Override
    protected boolean run(Trident trident) {
        return trident.hasGlint();
    }

}
