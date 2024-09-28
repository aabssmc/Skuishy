package lol.aabss.skuishy.elements.entities.conditions.multiple;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PiglinAbstract;

@Name("Hoglin/Piglin - Is Immune To Zombification")
@Description("Returns true if the hoglin or piglin is immune to zombification.")
@Examples({
        "if {_hoglin} is immune to zombification:",
        "\tbroadcast \"abc123abc123abc123\""
})
@Since("2.8")
public class CondIsImmuneToZombification extends EntityCondition<LivingEntity> {

    static {
        register(CondIsImmuneToZombification.class, "[hoglin|piglin] immune to zombif(ying|ication)", "livingentities");
    }

    @Override
    protected boolean run(LivingEntity entity) {
        if (entity instanceof Hoglin) {
            return ((Hoglin) entity).isImmuneToZombification();
        } else if (entity instanceof PiglinAbstract) {
            return ((PiglinAbstract) entity).isImmuneToZombification();
        }
        return false;
    }
}
