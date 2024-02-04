package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Goat;
import org.eclipse.jdt.annotation.NonNull;

@Name("Goat - Has Left Horn")
@Description("Returns true if the goat has the left horn.")
@Examples({
        "if last spawned goat has left horn:"
})
@Since("2.0")
public class CondHasLeftHorn extends PropertyCondition<Entity> {

    static {
        register(CondHasLeftHorn.class, PropertyType.HAVE, "[the] left horn", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Goat){
            return ((Goat) entity).hasLeftHorn();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "left horn";
    }
}
