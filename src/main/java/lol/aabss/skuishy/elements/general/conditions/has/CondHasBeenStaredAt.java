package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Enderman - Has Been Stared At")
@Description("Returns true if the enderman has been stared at.")
@Examples({
        "if target entity has been stared at:"
})
@Since("2.0")
public class CondHasBeenStaredAt extends PropertyCondition<LivingEntity> {

    static{
        if (Skript.methodExists(Enderman.class, "hasBeenStaredAt")) {
            register(CondHasBeenStaredAt.class, PropertyType.HAVE, "been stared at", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Enderman){
            return ((Enderman) entity).hasBeenStaredAt();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "been stared at";
    }
}
