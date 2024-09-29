package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Witch;

@Name("Witch - Is Drinking Potion")
@Description("True if the witch is drinking a potion.")
@Examples({
        "if {_witch} is drinking:"
})
@Since("2.8")
public class CondIsWitchDrinkingPotion extends EntityCondition<Witch> {

    static {
        register(CondIsWitchDrinkingPotion.class, "[witch] drinking [a] potion", "entities");
    }

    @Override
    protected boolean run(Witch witch) {
        return witch.isDrinkingPotion();
    }
}
