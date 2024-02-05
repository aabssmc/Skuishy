package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Bee - Has Nectar")
@Description("Returns true if the bee has nectar.")
@Examples({
        "if target entity has nectar:"
})
@Since("2.0")
public class CondHasNectar extends PropertyCondition<LivingEntity> {

    static {
        register(CondHasNectar.class, PropertyType.HAVE, "nectar", "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Bee){
            return ((Bee) entity).hasNectar();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "nectar";
    }
}
