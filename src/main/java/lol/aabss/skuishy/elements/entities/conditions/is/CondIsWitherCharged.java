package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;

@Name("Wither/Wither Skull - Is Charged")
@Description("Returns true if the wither or wither skull is charged.")
@Examples({
        "if last spawned wither is charged:"
})
@Since("2.8")
public class CondIsWitherCharged extends EntityCondition<Entity> {

    static {
        register(CondIsWitherCharged.class, "[wither [skull]] charged", "entities");
    }

    @Override
    protected boolean run(Entity entity) {
        if (entity instanceof WitherSkull) {
            return ((WitherSkull) entity).isCharged();
        } else if (entity instanceof Wither) {
            return ((Wither) entity).isCharged();
        }
        return false;
    }
}

