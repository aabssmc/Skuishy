package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.jetbrains.annotations.NotNull;

@Name("Fox - Is Leaping")
@Description("True if the fox is leaping.")
@Examples({
        "if {_fox} is leaping:",
        "\tset leaping state of {_fox} to false"
})
@Since("2.8")
public class CondIsLeaping extends PropertyCondition<Entity> {

    static {
        register(CondIsLeaping.class, "leaping", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Fox) {
            return ((Fox) entity).isLeaping();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "leaping";
    }
}
