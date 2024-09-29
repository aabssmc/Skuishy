package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Player - Sleep")
@Description("Makes a player sleep at a specified location.")
@Examples({
        "attempt to make player sleep at {_loc}"
})
@Since("2.0")
public class EffSleep extends EntityEffect<Player> {

    static {
        Skript.registerEffect(EffSleep.class,
                "(try|attempt) to make %players% sleep at %location%",
                "(:force) %players% [to] sleep at %location%"
        );
    }

    @Override
    protected void execute(Player player, Event event) {
        if (exprs.length >= 2 && exprs[1].getSingle(event) != null) {
            Location location = (Location) exprs[1].getSingle(event);
            if (location == null) return;
            player.sleep(location, tags.contains("force"));
        }
    }
}