package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Dolphin - Has Fish")
@Description("True if the dolphin has a fish:")
@Examples({
        "if {_dolphin} has fish:",
        "\tset fish state of {_dolphin} to false"
})
@Since("2.8")
public class CondHasFish extends PropertyCondition<Entity> {

    static {
        register(CondHasFish.class, PropertyType.HAVE, "[dolphin] has", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Dolphin) {
            return ((Dolphin) entity).hasFish();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "has fish";
    }
}
