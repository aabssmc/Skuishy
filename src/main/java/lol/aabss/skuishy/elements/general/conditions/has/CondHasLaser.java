package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Guardian - Has Laser")
@Description("Returns true if the guardian has a laser.")
@Examples({
        "if target entity has laser:"
})
@Since("2.0")
public class CondHasLaser extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.methodExists(Guardian.class, "hasLaser")) {
            register(CondHasLaser.class, PropertyType.HAVE, "[a] laser", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Guardian){
            return ((Guardian) entity).hasLaser();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "laser";
    }
}
