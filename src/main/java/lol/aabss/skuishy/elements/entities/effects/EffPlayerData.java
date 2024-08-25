package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.SimpleEntityEffect;
import org.bukkit.entity.Player;

@Name("Player - Player Data")
@Description(
        """
                Loads the players current location, health, inventory, motion, and other information from the <uuid>.dat file, in the <level-name>/playerdata/ folder.
                Note: This will overwrite the players current inventory, health, motion, etc, with the state from the saved dat file.

                Saves the players current location, health, inventory, motion, and other information into the <uuid>.dat file, in the <level-name>/playerdata/ folder.
                """
)
@Examples({
        "save player's data",
        "load player's current data"
})
@Since("2.1")
public class EffPlayerData extends SimpleEntityEffect<Player> {

    static {
        Skript.registerEffect(EffPlayerData.class,
                "(save|:load) %players%['s] [current] data"
        );
    }

    @Override
    protected void execute(Player player) {
        if (tags.contains("load")) {
            player.loadData();
        } else {
            player.saveData();
        }
    }
}
