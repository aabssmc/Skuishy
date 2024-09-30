package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;

@Name("Villager - Zombify")
@Description("Zombifies a villager, turning it into a zombie villager.")
@Examples({
        "make {_villager} zombify"
})
@Since("2.8")
public class EffVillagerZombify extends EntityEffect<Villager> {

    static {
        Skript.registerEffect(EffVillagerZombify.class,
                "make %entities% zombify",
                "zombify %entities%"
        );
    }

    @Override
    protected void execute(Villager villager, Event event) {
        villager.zombify();
    }
}
