package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;

@Name("Villager - Shake Head")
@Description("Makes a villager shake its head.")
@Examples({
        "make {_villager} shake head"
})
@Since("2.8")
public class EffVillagerShakeHead extends EntityEffect<Villager> {

    static {
        Skript.registerEffect(EffVillagerShakeHead.class,
                "make %entities% shake head"
        );
    }

    @Override
    protected void execute(Villager villager, Event event) {
        villager.shakeHead();
    }
}
