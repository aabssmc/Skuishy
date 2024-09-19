package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Allay;

@Name("Entity - Can Duplicate")
@Description("Returns true if the allay can duplicate.")
@Examples({
        "if event-entity can duplicate:"
})
@Since("2.0")
public class CondCanDuplicate extends EntityCondition<Allay> {
    static{
        if (Skript.classExists("org.bukkit.entity.Allay")) {
            register(CondCanDuplicate.class, PropertyType.CAN, "[allay] dup(e|licate)", "livingentities");
        }
    }

    @Override
    protected boolean run(Allay allay) {
        return allay.canDuplicate();
    }

}
