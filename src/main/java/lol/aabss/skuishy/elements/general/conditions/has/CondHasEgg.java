package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Turtle;
import org.jetbrains.annotations.NotNull;

@Name("Turtle - Has Egg")
@Description("Returns true if the turtle has a egg.")
@Examples({
        "if target entity has a egg:"
})
@Since("2.0")
public class CondHasEgg extends PropertyCondition<LivingEntity> {

    static{
        register(CondHasEgg.class, PropertyType.HAVE, "[a] egg", "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Turtle){
            return ((Turtle) entity).hasEgg();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "egg";
    }
}
