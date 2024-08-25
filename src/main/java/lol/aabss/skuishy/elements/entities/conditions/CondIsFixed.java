package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.jetbrains.annotations.NotNull;

@Name("Item Frame - Is Fixed")
@Description("True if the item frame is fixed.")
@Examples({
        "if {_itemframe} is itemframe fixed:",
        "\tset itemframe fixed mode of {_itemframe} to false"
})
@Since("2.8")
public class CondIsFixed extends PropertyCondition<Entity> {

    static {
        register(CondIsFixed.class, "item[ |-]frame fixed", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof ItemFrame) {
            return ((ItemFrame) entity).isFixed();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "fixed";
    }
}
