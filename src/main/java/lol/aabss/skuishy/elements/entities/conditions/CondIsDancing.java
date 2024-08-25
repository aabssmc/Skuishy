package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Allay - Is Dancing")
@Description("True if the allay is dancing.")
@Examples({
        "if {_allay} is dancing:",
        "\tset dash state of {_allay} to false"
})
@Since("2.8")
public class CondIsDancing extends PropertyCondition<Entity> {

    static {
        register(CondIsDancing.class, "[allay] dancing", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Allay) {
            return ((Allay) entity).isDancing();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "dancing";
    }
}
