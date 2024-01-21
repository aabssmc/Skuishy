package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand - Can Move")
@Description("Returns true if the armor stand can move.")
@Examples({
        "if last spawned armor stand can move:"
})
@Since("2.0")
public class CondCanMove extends PropertyCondition<Entity> {

    static{
        register(CondCanMove.class,
                PropertyType.CAN,
                "move",
                "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).canMove();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "move";
    }
}
