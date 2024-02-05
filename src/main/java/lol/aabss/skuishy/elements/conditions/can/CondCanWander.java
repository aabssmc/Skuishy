package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.jetbrains.annotations.NotNull;

@Name("Slime - Can Wander")
@Description("Returns true if the slime can wander.")
@Examples({
        "if last spawned slime can wander:"
})
@Since("2.0")
public class CondCanWander extends PropertyCondition<LivingEntity> {

    static{
        register(CondCanWander.class,
                PropertyType.CAN,
                "wander",
                "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Slime){
            return ((Slime) entity).canWander();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "wander";
    }
}
