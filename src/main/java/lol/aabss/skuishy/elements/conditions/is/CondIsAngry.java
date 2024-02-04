package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Wolf;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Is Angry")
@Description("Returns true if the entity is angry.")
@Examples({
        "if last spawned wolf is mad:"
})
@Since("2.0")
public class CondIsAngry extends PropertyCondition<Entity> {

    static{
        register(CondIsAngry.class,
                PropertyType.BE,
                "(angry|mad)",
                "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof PigZombie){
            return ((PigZombie) entity).isAngry();
        }
        else if (entity instanceof Wolf){
            return ((Wolf) entity).isAngry();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "angry";
    }
}
