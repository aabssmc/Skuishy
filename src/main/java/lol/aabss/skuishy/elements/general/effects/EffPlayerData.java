package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
public class EffPlayerData extends Effect {

    static {
        Skript.registerEffect(EffPlayerData.class,
                "(save|:load) %players%['s] [current] data"
        );
    }

    private boolean load;
    private Expression<Player> players;

    @Override
    protected void execute(@NotNull Event e) {
        for (Player p : players.getArray(e)){
            if (load) {
                p.loadData();
            } else {
                p.saveData();
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "load/save data of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        load = parseResult.hasTag("load");
        players = (Expression<Player>) exprs[0];
        return true;
    }
}
