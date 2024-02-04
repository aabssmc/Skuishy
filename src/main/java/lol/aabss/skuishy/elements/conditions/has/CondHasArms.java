package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.NonNull;

@Name("Armor Stand - Has Arms")
@Description("Returns true if the slime has arms.")
@Examples({
        "if last spawned armor stand has arms:"
})
@Since("2.0")
public class CondHasArms extends PropertyCondition<Entity> {

    static{
        register(CondHasArms.class,
                PropertyType.HAVE,
                "arms",
                "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ArmorStand){
            return ((ArmorStand) entity).hasArms();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "arms";
    }
}
