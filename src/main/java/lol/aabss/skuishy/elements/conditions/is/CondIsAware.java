package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.eclipse.jdt.annotation.NonNull;

@Name("Entity - Is Aware")
@Description("Returns true if the entity is aware.")
@Examples({
        "if event-entity is aware:"
})
@Since("2.0")
public class CondIsAware extends PropertyCondition<Entity> {

    static{
        register(CondIsAware.class, PropertyType.BE, "aware", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Mob){
            return ((Mob) entity).isAware();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "aware";
    }
}
