package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Dolphin - Has Fish")
@Description("Returns true if the dolphin has a fish.")
@Examples({
        "if target entity has a fish:"
})
@Since("2.0")
public class CondHasFish extends PropertyCondition<Entity> {

    static {
        register(CondHasFish.class, PropertyType.HAVE, "[a] fish", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Dolphin){
            return ((Dolphin) entity).hasFish();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "fish";
    }
}
