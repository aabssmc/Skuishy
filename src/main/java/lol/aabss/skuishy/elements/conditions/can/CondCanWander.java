package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;
import org.eclipse.jdt.annotation.NonNull;

@Name("Slime - Can Wander")
@Description("Returns true if the slime can wander.")
@Examples({
        "if last spawned slime can wander:"
})
@Since("2.0")
public class CondCanWander extends PropertyCondition<Entity> {

    static{
        register(CondCanWander.class,
                PropertyType.CAN,
                "wander",
                "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Slime){
            return ((Slime) entity).canWander();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "wander";
    }
}
