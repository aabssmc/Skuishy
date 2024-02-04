package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.eclipse.jdt.annotation.NonNull;

@Name("Entity - Can Break Doors")
@Description("Returns true if the zombie can break a door.")
@Examples({
        "if event-entity can break doors:"
})
@Since("2.0")
public class CondCanBreakDoors extends PropertyCondition<Entity> {

    static{
        register(CondCanBreakDoors.class,
                PropertyType.CAN,
                "(break|destroy) door[s]",
                "entities"
        );
    }

    @Override
    public boolean check(Entity o) {
        if (o instanceof Zombie z){
            return z.canBreakDoors();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "can break doors";
    }
}
