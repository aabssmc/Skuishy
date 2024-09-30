package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;

@Name("Villager - Increase Level")
@Description("Increases the level of a villager by a specified amount.")
@Examples({
        "increase level of {_villager} by 1"
})
@Since("2.8")
public class EffVillagerIncreaseLevel extends EntityEffect<Villager> {

    static {
        Skript.registerEffect(EffVillagerIncreaseLevel.class,
                "increase level of %entities% by [amount] %integer%"
        );
    }

    @Override
    protected void execute(Villager villager, Event event) {
        if (exprs.length >= 2 && exprs[1].getSingle(event) != null) {
            Integer integer = (Integer) exprs[1].getSingle(event);
            if (integer == null) return;
            villager.increaseLevel(integer);
        }
    }
}
