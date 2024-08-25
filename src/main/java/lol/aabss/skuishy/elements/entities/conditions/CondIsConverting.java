package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

@Name("Skeleton/Zombie - Is Converting")
@Description("True if the skeleton/zombie is converting.")
@Examples({
        "if {_skeleton} is converting:",
        "\tset conversion time of {_skeleton} to 100"
})
@Since("2.8")
public class CondIsConverting extends PropertyCondition<Entity> {

    static {
        register(CondIsConverting.class, "converting", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Skeleton) {
            return ((Skeleton) entity).isConverting();
        } else if (entity instanceof Zombie) {
            return ((Zombie) entity).isConverting();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "converting";
    }
}
