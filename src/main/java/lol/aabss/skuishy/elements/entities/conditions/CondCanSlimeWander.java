package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Slime;

@Name("Slime - Can Wander")
@Description("Returns true if the slime can wander.")
@Examples({
        "if last spawned slime can wander:"
})
@Since("2.0")
public class CondCanSlimeWander extends EntityCondition<Slime> {

    static {
        register(CondCanSlimeWander.class, PropertyType.CAN, "wander", "livingentities");
    }

    @Override
    protected boolean run(Slime slime) {
        return slime.canWander();
    }
}
