package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.Event;

@Name("Villager - Reset Offers")
@Description("Makes a villager reset their offers.")
@Examples({
        "make {_villager} reset offers"
})
@Since("2.8")
public class EffVillagerResetOffers extends EntityEffect<AbstractVillager> {

    static {
        Skript.registerEffect(EffVillagerResetOffers.class,
                "make %entities% reset offers",
                "reset [the] offers of %entities%"
        );
    }

    @Override
    protected void execute(AbstractVillager villager, Event event) {
        villager.resetOffers();
    }

}
