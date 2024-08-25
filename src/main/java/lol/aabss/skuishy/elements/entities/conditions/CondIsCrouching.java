package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.jetbrains.annotations.NotNull;

@Name("Fox - Is Crouching")
@Description("True if the fox is crouching.")
@Examples({
        "if {_fox} is crouching:",
        "\tset crouching state of {_fox} to false"
})
@Since("2.8")
public class CondIsCrouching extends PropertyCondition<Entity> {

    static {
        register(CondIsCrouching.class, "crouching", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Fox) {
            return ((Fox) entity).isCrouching();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "crouching";
    }
}
