package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Camel;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Camel - Is Dashing")
@Description("True if the camel is dashing.")
@Examples({
        "if {_camel} is dashing:",
        "\tset dash state of {_camel} to false"
})
@Since("2.8")
public class CondIsDashing extends PropertyCondition<Entity> {

    static {
        register(CondIsDashing.class, "[camel] dashing", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Camel) {
            return ((Camel) entity).isDashing();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "dashing";
    }
}
