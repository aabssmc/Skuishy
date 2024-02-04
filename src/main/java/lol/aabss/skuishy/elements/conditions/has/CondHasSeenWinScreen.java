package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Name("Player - Has Seen Win Screen")
@Description("Returns true if the player has seen the win screen.")
@Examples({
        "if player has seen win screen:"
})
@Since("2.0")
public class CondHasSeenWinScreen extends PropertyCondition<Player> {

    static{
        register(CondHasSeenWinScreen.class, PropertyType.HAVE, "seen [the] win[ ]screen", "players");
    }

    @Override
    public boolean check(Player player) {
        return player.hasSeenWinScreen();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "seen win screen";
    }
}
