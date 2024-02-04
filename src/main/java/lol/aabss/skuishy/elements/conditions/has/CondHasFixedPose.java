package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Has Fixed Pose")
@Description("Returns true if the entity has a fixed pose.")
@Examples({
        "if target entity has fixed pose:"
})
@Since("2.0")
public class CondHasFixedPose extends PropertyCondition<Entity> {

    static {
        register(CondHasFixedPose.class, PropertyType.HAVE, "[a] fixed pose", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        return entity.hasFixedPose();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "fixed pose";
    }
}
