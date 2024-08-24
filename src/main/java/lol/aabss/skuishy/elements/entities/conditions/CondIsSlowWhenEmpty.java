package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.NotNull;

@Name("Minecart - Is Slow When Empty")
@Description("True if the minecart is slow when empty.")
@Examples({
        "if {_minecart} is not slow when empty:",
        "\tset slow when empty state of {_minecart} to true"
})
@Since("2.8")
public class CondIsSlowWhenEmpty extends PropertyCondition<Entity> {

    static {
        register(CondIsSlowWhenEmpty.class, "slow when empty", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Minecart) {
            return ((Minecart) entity).isSlowWhenEmpty();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "slow when empty";
    }
}