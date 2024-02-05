package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Hoglin - Is Able To Be Hunted")
@Description("Returns true if the hoglin is able to be hunted.")
@Examples({
        "if last spawned hoglin is able to be hunted:"
})
@Since("2.0")
public class CondIsAbleToBeHunted extends PropertyCondition<LivingEntity> {

    static {
        register(CondIsAbleToBeHunted.class,
                PropertyType.BE,
                "able to be hunted",
                "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Hoglin){
            return ((Hoglin) entity).isAbleToBeHunted();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "hunted";
    }
}
