package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vex;
import org.jetbrains.annotations.NotNull;

@Name("Vex - Has Limited Lifetime")
@Description("Returns true if the vex has limited lifetime.")
@Examples({
        "if target entity has limited lifetime:"
})
@Since("2.0")
public class CondHasLimitedLifetime extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.methodExists(Vex.class, "hasLimitedLifetime")) {
            register(CondHasLimitedLifetime.class, PropertyType.HAVE, "limited lifetime", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Vex){
            return ((Vex) entity).hasLimitedLifetime();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "limited lifetime";
    }
}
