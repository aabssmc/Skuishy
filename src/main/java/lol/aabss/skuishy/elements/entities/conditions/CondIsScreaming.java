package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;

@Name("Enderman/Goat - Is Screaming")
@Description("True if the enderman or goat is screaming.")
@Examples({
        "if {_enderman} is screaming:",
        "\tset screaming state of {_enderman} to false"
})
@Since("2.8")
public class CondIsScreaming extends EntityCondition<LivingEntity> {

    static {
        register(CondIsScreaming.class, "[enderman|goat] screaming", "entities");
    }

    @Override
    protected boolean run(LivingEntity entity) {
        if (entity instanceof Enderman) {
            return ((Enderman) entity).isScreaming();
        } else if (entity instanceof Goat) {
            return ((Goat) entity).isScreaming();
        }
        return false;
    }
}
