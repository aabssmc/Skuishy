package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Chicken;
import org.jetbrains.annotations.NotNull;

@Name("Chicken - Is Chicken Jockey")
@Description("True if the chicken is a chicken jockey.")
@Examples({
        "if {_chicken} is chicken jockey:",
        "\tset chicken jockey state of {_chicken} to false"
})
@Since("2.8")
public class CondIsChickenJockey extends PropertyCondition<Entity> {

    static {
        register(CondIsChickenJockey.class, "[a] chicken jockey", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Chicken) {
            return ((Chicken) entity).isChickenJockey();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "chicken jockey";
    }
}
