package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Armor Stand - Has Base Plate")
@Description("Returns true if the armor stand has a base plate.")
@Examples({
        "if target entity has baseplate:"
})
@Since("2.0")
public class CondHasBasePlate extends PropertyCondition<Entity> {

    static{
        register(CondHasBasePlate.class, PropertyType.HAVE, "[a|the] base[ ]plate", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand){
            return ((ArmorStand) entity).hasBasePlate();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "base plate";
    }
}
