package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.jetbrains.annotations.NotNull;

@Name("Llama - In Caravan")
@Description("True if the llama is in a caravan.")
@Examples({
        "if {_llama} is in caravan:",
        "\tbroadcast \"very good!\""
})
@Since("2.8")
public class CondIsInCaravan extends PropertyCondition<Entity> {

    static {
        register(CondIsInCaravan.class, "[llama] in caravan", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Llama) {
            return ((Llama) entity).inCaravan();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "caravan";
    }
}
