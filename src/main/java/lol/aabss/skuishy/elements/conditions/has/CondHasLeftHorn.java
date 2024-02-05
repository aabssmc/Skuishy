package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Goat - Has Left Horn")
@Description("Returns true if the goat has the left horn.")
@Examples({
        "if last spawned goat has left horn:"
})
@Since("2.0")
public class CondHasLeftHorn extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.methodExists(Goat.class, "hasLeftHorn")) {
            register(CondHasLeftHorn.class, PropertyType.HAVE, "[the] left horn", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Goat){
            return ((Goat) entity).hasLeftHorn();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "left horn";
    }
}
