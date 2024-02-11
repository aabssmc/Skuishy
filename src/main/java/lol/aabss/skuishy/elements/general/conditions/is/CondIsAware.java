package lol.aabss.skuishy.elements.general.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Is Aware")
@Description("Returns true if the entity is aware.")
@Examples({
        "if event-entity is aware:"
})
@Since("2.0")
public class CondIsAware extends PropertyCondition<LivingEntity> {

    static{
        register(CondIsAware.class, PropertyType.BE, "aware", "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Mob){
            return ((Mob) entity).isAware();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "aware";
    }
}
