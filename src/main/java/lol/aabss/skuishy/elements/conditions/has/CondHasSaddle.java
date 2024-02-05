package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Steerable;
import org.jetbrains.annotations.NotNull;

@Name("Jukebox - Has Record")
@Description("Returns true if the jukebox has a record.")
@Examples({
        "if {_b} has a record:"
})
@Since("2.0")
public class CondHasSaddle extends PropertyCondition<LivingEntity> {

    static{
        register(CondHasSaddle.class, PropertyType.HAVE, "[a] saddle", "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Steerable){
            return ((Steerable) entity).hasSaddle();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "saddle";
    }
}
