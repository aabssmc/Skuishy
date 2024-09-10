package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Player - Show Win Screen")
@Description("Shows the win screen to a player.")
@Examples({
        "show win screen to event-player"
})
@Since("2.0")
public class EffShowWinScreen extends EntityEffect<Player> {

    static{
        if (Skript.methodExists(Player.class, "showWinScreen")) {
            Skript.registerEffect(EffShowWinScreen.class,
                    "show [the] win screen to %players%",
                    "make %players% see the win screen"
            );
        }
    }

    @Override
    protected void execute(Player player, Event event) {
        player.showWinScreen();
    }
}
