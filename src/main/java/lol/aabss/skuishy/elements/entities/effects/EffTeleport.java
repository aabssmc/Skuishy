package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

@Name("Enderman - Teleport")
@Description("Tries to make an enderman teleport either randomly or towards an entity.")
@Examples({
        "make {_e} teleport towards player"
})
@Since("2.3")
public class EffTeleport extends EntityEffect<Enderman> {

    static {
        if (Skript.methodExists(Enderman.class, "teleport") && Skript.methodExists(Enderman.class, "teleportTowards", Entity.class)) {
            Skript.registerEffect(EffTeleport.class,
                    "[(try|attempt) to] [:randomly] make %entities% teleport [towards %-entity%]"
            );
        }
    }

    @Override
    protected void execute(Enderman enderman, Event event) {
        if (exprs.length >= 2 && exprs[1].getSingle(event) != null) {
            Entity entity = (Entity) exprs[1].getSingle(event);
            if (entity == null) return;
            enderman.teleportTowards(entity);
        } else if (tags.contains("random")) {
            enderman.teleportRandomly();
        } else {
            enderman.teleport();
        }
    }

}
