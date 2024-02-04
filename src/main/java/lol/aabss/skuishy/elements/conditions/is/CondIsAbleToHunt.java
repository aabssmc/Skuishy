package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Piglin;
import org.eclipse.jdt.annotation.NonNull;

@Name("Piglin - Is Able To Be Hunted")
@Description("Returns true if the piglin is able to hunt.")
@Examples({
        "if last spawned piglin is able to hunt:"
})
@Since("2.0")
public class CondIsAbleToHunt extends PropertyCondition<Entity> {

    static {
        register(CondIsAbleToHunt.class,
                PropertyType.BE,
                "able to hunt",
                "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Piglin){
            return ((Piglin) entity).isAbleToHunt();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "hunt";
    }
}
