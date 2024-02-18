package lol.aabss.skuishy.elements.general.conditions.is;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.block.data.Hangable;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Is Hanging")
@Description("Entity is hanging.")
@Examples({
        "if entity is hanging:",
        "\tbroadcast \"%event-entity% is hanging\""
})
@Since("1.6")

public class CondIsHanging extends PropertyCondition<Entity> {

    static{
        if (Skript.classExists("org.bukkit.block.data.Hangable")) {
            register(CondIsHanging.class,
                    "(hanging|hung)",
                    "entities");
        }
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Hangable){
            return ((Hangable) entity).isHanging();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "hanging";
    }
}


