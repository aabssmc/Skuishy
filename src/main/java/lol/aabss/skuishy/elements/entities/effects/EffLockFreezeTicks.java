package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.SimpleEntityEffect;
import org.bukkit.entity.Entity;

@Name("Entity - Lock Freeze Ticks")
@Description("Locks/Unlocks freeze ticks of player")
@Examples({
        "lock freeze ticks of player"
})
@Since("1.9")

public class EffLockFreezeTicks extends SimpleEntityEffect<Entity> {

    static{
        if (Skript.methodExists(Entity.class, "lockFreezeTicks", Boolean.class)) {
            Skript.registerEffect(EffLockFreezeTicks.class,
                    "[:un]lock (freeze|frozen) ticks of %entities%"
            );
        }
    }

    @Override
    protected void execute(Entity entity) {
        entity.lockFreezeTicks(tags.contains("un"));
    }
}
