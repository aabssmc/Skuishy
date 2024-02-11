package lol.aabss.skuishy.elements.general.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Goat - Has Right Horn")
@Description("Returns true if the goat has the right horn.")
@Examples({
        "if last spawned goat has right horn:"
})
@Since("2.0")
public class CondHasRightHorn extends PropertyCondition<LivingEntity> {

    static {
        if (Skript.methodExists(Goat.class, "hasRightHorn")) {
            register(CondHasRightHorn.class, PropertyType.HAVE, "[the] right horn", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Goat){
            return ((Goat) entity).hasRightHorn();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "right horn";
    }
}
