package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Object - Has Gravity")
@Description("Returns true if the object has gravity.")
@Examples({
        "if target entity has caravan trail:"
})
@Since("2.0")
public class CondHasGravity extends PropertyCondition<Object> {

    static{
        register(CondHasGravity.class, PropertyType.HAVE, "gravity", "entities/materials");
    }

    @Override
    public boolean check(Object o) {
        if (o instanceof Entity){
            return ((Entity) o).hasGravity();
        }
        else if (o instanceof Material){
            return ((Material) o).hasGravity();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "gravity";
    }
}
