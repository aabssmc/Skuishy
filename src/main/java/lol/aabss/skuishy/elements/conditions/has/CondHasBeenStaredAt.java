package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Enderman - Has Been Stared At")
@Description("Returns true if the enderman has been stared at.")
@Examples({
        "if target entity has been stared at:"
})
@Since("2.0")
public class CondHasBeenStaredAt extends PropertyCondition<Entity> {

    static{
        register(CondHasBeenStaredAt.class, PropertyType.HAVE, "been stared at", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Enderman){
            return ((Enderman) entity).hasBeenStaredAt();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "been stared at";
    }
}
