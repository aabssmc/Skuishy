package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Guardian;
import org.eclipse.jdt.annotation.NonNull;

@Name("Guardian - Has Laser")
@Description("Returns true if the guardian has a laser.")
@Examples({
        "if target entity has laser:"
})
@Since("2.0")
public class CondHasLaser extends PropertyCondition<Entity> {

    static {
        register(CondHasLaser.class, PropertyType.HAVE, "[a] laser", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Guardian){
            return ((Guardian) entity).hasLaser();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "laser";
    }
}
