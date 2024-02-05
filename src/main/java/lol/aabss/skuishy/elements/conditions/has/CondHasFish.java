package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Dolphin - Has Fish")
@Description("Returns true if the dolphin has a fish.")
@Examples({
        "if target entity has a fish:"
})
@Since("2.0")
public class CondHasFish extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.methodExists(Dolphin.class, "hasFish")) {
            register(CondHasFish.class, PropertyType.HAVE, "[a] fish", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Dolphin){
            return ((Dolphin) entity).hasFish();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "fish";
    }
}
