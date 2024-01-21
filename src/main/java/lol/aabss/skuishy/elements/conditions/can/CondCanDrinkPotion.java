package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WanderingTrader;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Can Drink Potion")
@Description("Returns true if the wandering trader can drink a potion.")
@Examples({
        "if event-entity can drink potion:"
})
@Since("2.0")
public class CondCanDrinkPotion extends PropertyCondition<LivingEntity> {

    static{
        register(CondCanDrinkPotion.class,
                PropertyType.CAN,
                "(drink|consume) potion[s]",
                "livingentities"
        );
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        if (livingEntity instanceof WanderingTrader e){
            return e.canDrinkPotion();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "can drink potion";
    }
}
