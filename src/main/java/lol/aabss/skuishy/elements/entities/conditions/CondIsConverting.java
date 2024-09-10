package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;

@Name("Skeleton/Zombie - Is Converting")
@Description("True if the skeleton/zombie is converting.")
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
        }
        return false;
    }
}
