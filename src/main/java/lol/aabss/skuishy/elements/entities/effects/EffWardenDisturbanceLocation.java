package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Warden;
import org.bukkit.event.Event;

@Name("Warden - Disturbance Location")
@Description("Sets the disturbance location of a warden to a location.")
@Examples({
        "set disturbance location of {_warden} to target location"
})
@Since("2.8")
public class EffWardenDisturbanceLocation extends EntityEffect<Warden> {

    static {
        if (Skript.classExists("org.bukkit.entity.Warden")) {
            Skript.registerEffect(EffWardenDisturbanceLocation.class,
                    "set disturbance location of %entities% to %location%",
                    "set %entities%'[s] disturbance location to %location%"
            );
        }
    }

    @Override
    protected void execute(Warden warden, Event event) {
        if (exprs.length >= 2 && exprs[1].getSingle(event) != null) {
            Location location = (Location) exprs[1].getSingle(event);
            if (location == null) return;
            warden.setDisturbanceLocation(location);
        }
    }
}
