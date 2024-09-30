package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;

@Name("Fox/Wolf - Is Interested")
@Description("True if the fox or wolf is interested.")
@Examples({
        "if {_fox} is interested:",
        "\tset interested state of {_fox} to false"
})
@Since("2.8")
public class CondIsInterested extends EntityCondition<LivingEntity> {

    static {
        register(CondIsInterested.class, "[fox] interested", "entities");
    }

    @Override
    protected boolean run(LivingEntity entity) {
        if (entity instanceof Fox) {
            return ((Fox) entity).isInterested();
        } else if (entity instanceof Wolf) {
            return ((Wolf) entity).isInterested();
        }
        return false;
    }

}
