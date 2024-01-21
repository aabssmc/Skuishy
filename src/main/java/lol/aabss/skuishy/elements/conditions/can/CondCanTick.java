package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand - Can Tick")
@Description("Returns true if the armor stand can tick.")
@Examples({
        "if last spawned armor stand can tick:"
})
@Since("2.0")
public class CondCanTick extends PropertyCondition<Entity> {

    static{
        register(CondCanTick.class,
                PropertyType.CAN,
                "tick",
                "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).canTick();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "tick";
    }
}
