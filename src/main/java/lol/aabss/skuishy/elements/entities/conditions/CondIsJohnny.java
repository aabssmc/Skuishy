package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Vindicator;
import org.jetbrains.annotations.NotNull;

@Name("Vindicator - Is Johnny")
@Description("True if the vindicator is a johnny.")
@Examples({
        "if {_vindicator} is johnny:",
        "\tset johnny state of {_vindicator} to false"
})
@Since("2.8")
public class CondIsJohnny extends PropertyCondition<Entity> {

    static {
        register(CondIsJohnny.class, "johnny", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Vindicator) {
            return ((Vindicator) entity).isJohnny();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "johnny";
    }
}