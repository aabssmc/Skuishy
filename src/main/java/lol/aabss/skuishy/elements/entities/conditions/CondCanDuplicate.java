package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Allay;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Can Duplicate")
@Description("Returns true if the allay can duplicate.")
@Examples({
        "if event-entity can duplicate:"
})
@Since("2.0")
public class CondCanDuplicate extends PropertyCondition<LivingEntity> {
    static{
        if (Skript.classExists("org.bukkit.entity.Allay")) {
            register(CondCanDuplicate.class,
                    PropertyType.CAN,
                    "dup(e|licate)",
                    "livingentities"
            );
        }
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        if (livingEntity instanceof Allay e){
            return e.canDuplicate();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "can duplicate";
    }
}
