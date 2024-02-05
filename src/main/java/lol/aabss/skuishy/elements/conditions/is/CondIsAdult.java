package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Is Adult")
@Description("Returns true if the entity is an adult.")
@Examples({
        "if event-entity is an adult:"
})
@Since("2.0")
public class CondIsAdult extends PropertyCondition<LivingEntity> {

    static {
        register(CondIsAdult.class,
                PropertyType.BE,
                "[a[n]] adult",
                "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Ageable){
            return ((Ageable) entity).isAdult();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "adult";
    }
}
