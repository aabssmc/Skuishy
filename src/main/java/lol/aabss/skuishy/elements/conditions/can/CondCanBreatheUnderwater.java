package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Can Breathe Underwater")
@Description("Returns true if the entity can breathe underwater.")
@Examples({
        "if event-entity can breathe underwater:"
})
@Since("2.0")
public class CondCanBreatheUnderwater extends PropertyCondition<LivingEntity> {

    static{
        if (Skript.methodExists(LivingEntity.class, "canBreatheUnderwater")) {
            register(CondCanBreatheUnderwater.class,
                    PropertyType.CAN,
                    "breathe under[ ]water",
                    "livingentities"
            );
        }
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        return livingEntity.canBreatheUnderwater();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "can breathe underwater";
    }
}
