package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Can Breed")
@Description("Returns true if the entity can breed.")
@Examples({
        "if event-entity can breed:"
})
@Since("2.0")
public class CondCanBreed extends PropertyCondition<LivingEntity> {

    static{
        register(CondCanBreed.class,
                PropertyType.CAN,
                "breed",
                "livingentities"
        );
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Breedable b){
            return b.canBreed();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "can breed";
    }
}
