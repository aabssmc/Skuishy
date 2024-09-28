package lol.aabss.skuishy.elements.entities.conditions.multiple;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.*;

@Name("Skeleton/Zombie/Hoglin - Is Converting")
@Description("True if the skeleton/zombie/hoglin is converting.")
@Examples({
        "if {_skeleton} is converting:",
        "\tset conversion time of {_skeleton} to 100"
})
@Since("2.8")
public class CondIsConverting extends EntityCondition<Entity> {

    static {
        register(CondIsConverting.class, "converting", "entities");
    }

    @Override
    protected boolean run(Entity entity) {
        if (entity instanceof Skeleton) {
            return ((Skeleton) entity).isConverting();
        } else if (entity instanceof Zombie) {
            return ((Zombie) entity).isConverting();
        } else if (entity instanceof Hoglin) {
            return ((Hoglin) entity).isConverting();
        } else if (entity instanceof PiglinAbstract) {
            return ((PiglinAbstract) entity).isConverting();
        }
        return false;
    }
}
