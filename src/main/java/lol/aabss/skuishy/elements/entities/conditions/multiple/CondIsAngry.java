package lol.aabss.skuishy.elements.entities.conditions.multiple;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Wolf;

@Name("Entity - Is Angry")
@Description("Returns true if the entity is angry.")
@Examples({
        "if last spawned wolf is mad:"
})
@Since("2.0")
public class CondIsAngry extends EntityCondition<LivingEntity> {

    static{
        register(CondIsAngry.class, PropertyType.BE, "(angry|mad)", "livingentities");
    }

    @Override
    protected boolean run(LivingEntity entity) {
        if (entity instanceof PigZombie){
            return ((PigZombie) entity).isAngry();
        } else if (entity instanceof Wolf){
            return ((Wolf) entity).isAngry();
        }
        return false;
    }
}
