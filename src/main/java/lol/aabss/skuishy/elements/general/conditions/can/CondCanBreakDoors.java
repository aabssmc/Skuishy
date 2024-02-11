package lol.aabss.skuishy.elements.general.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Can Break Doors")
@Description("Returns true if the zombie can break a door.")
@Examples({
        "if event-entity can break doors:"
})
@Since("2.0")
public class CondCanBreakDoors extends PropertyCondition<LivingEntity> {

    static{
        register(CondCanBreakDoors.class,
                PropertyType.CAN,
                "(break|destroy) door[s]",
                "livingentities"
        );
    }

    @Override
    public boolean check(LivingEntity o) {
        if (o instanceof Zombie z){
            return z.canBreakDoors();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "can break doors";
    }
}
