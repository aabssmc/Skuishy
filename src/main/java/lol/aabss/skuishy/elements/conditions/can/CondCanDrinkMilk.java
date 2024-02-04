package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WanderingTrader;
import org.eclipse.jdt.annotation.NonNull;

@Name("Entity - Can Drink Milk")
@Description("Returns true if the wander trader can drink milk.")
@Examples({
        "if event-entity can drink milk:"
})
@Since("2.0")
public class CondCanDrinkMilk extends PropertyCondition<LivingEntity> {

    static{
        register(CondCanDrinkMilk.class,
                PropertyType.CAN,
                "(drink|consume) milk",
                "livingentities"
        );
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        if (livingEntity instanceof WanderingTrader e){
            return e.canDrinkMilk();
        }
        return false;
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "can drink milk";
    }
}
