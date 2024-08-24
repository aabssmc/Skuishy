package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.jetbrains.annotations.NotNull;

@Name("Iron Golem - Is Player Created")
@Description("True if the iron golem is player created.")
@Examples({
        "if {_golem} is player created:",
        "\tset player created state of {_golem} to false"
})
@Since("2.8")
public class CondIsPlayerCreated extends PropertyCondition<Entity> {

    static {
        register(CondIsPlayerCreated.class, "[[iron] golem] player created", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof IronGolem) {
            return ((IronGolem) entity).isPlayerCreated();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "player created";
    }
}