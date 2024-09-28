package lol.aabss.skuishy.elements.entities.conditions.multiple;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Allay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Piglin;

@Name("Allay/Parrot/Piglin - Is Dancing")
@Description("True if the allay, parrot or piglin is dancing.")
@Examples({
        "if {_allay} is dancing:",
        "\tset dance state of {_allay} to false"
})
@Since("2.8")
public class CondIsDancing extends EntityCondition<LivingEntity> {

    static {
        register(CondIsDancing.class, "[allay|parrot|piglin] dancing", "entities");
    }

    @Override
    protected boolean run(LivingEntity entity) {
        if (entity instanceof Allay) {
            return ((Allay) entity).isDancing();
        } else if (entity instanceof Parrot) {
            return ((Parrot) entity).isDancing();
        }else if (entity instanceof Piglin) {
            return ((Piglin) entity).isDancing();
        }
        return false;
    }

}
