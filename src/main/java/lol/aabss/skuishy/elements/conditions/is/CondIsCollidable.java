package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Living Entity - Is Collidable")
@Description("Returns true if the entity is collidable.")
@Examples({
        "if event-entity is collidable:"
})
@Since("2.0")
public class CondIsCollidable extends PropertyCondition<LivingEntity> {

    static {
        register(CondIsCollidable.class, PropertyType.BE,"collidable", "livingentities");
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        return livingEntity.isCollidable();
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "collidable";
    }
}
