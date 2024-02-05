package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Is Aggressive")
@Description("Returns true if the entity is aggressive.")
@Examples({
        "if event-entity is aggressive:"
})
@Since("2.0")
public class CondIsAggressive extends PropertyCondition<Entity> {

    static{
        if (Skript.methodExists(Mob.class, "isAggressive")) {
            register(CondIsAggressive.class,
                    PropertyType.BE,
                    "aggressive",
                    "entities");
        }
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Mob){
            return ((Mob) entity).isAggressive();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "aggressive";
    }
}
