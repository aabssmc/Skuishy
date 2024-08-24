package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.jetbrains.annotations.NotNull;

@Name("Llama - Has Caravan Tail")
@Description("True if the llama has a caravan tail.")
@Examples({
        "if {_llama} has caravan tail:",
        "\tbroadcast \"very good!\""
})
@Since("2.8")
public class CondHasCaravanTail extends PropertyCondition<Entity> {

    static {
        register(CondHasCaravanTail.class, PropertyType.HAVE, "[llama] caravan tail", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Llama) {
            return ((Llama) entity).hasCaravanTail();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "caravan tail";
    }
}
