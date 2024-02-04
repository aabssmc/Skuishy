package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Entity - Can Breathe Underwater")
@Description("Returns true if the entity can breathe underwater.")
@Examples({
        "if event-entity can breathe underwater:"
})
@Since("2.0")
public class CondCanBreatheUnderwater extends PropertyCondition<LivingEntity> {

    static{
        register(CondCanBreatheUnderwater.class,
                PropertyType.CAN,
                "breathe under[ ]water",
                "livingentities"
        );
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        return livingEntity.canBreatheUnderwater();
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "can breathe underwater";
    }
}
