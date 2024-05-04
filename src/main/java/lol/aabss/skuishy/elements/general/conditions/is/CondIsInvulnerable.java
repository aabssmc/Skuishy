package lol.aabss.skuishy.elements.general.conditions.is;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;

@Name("Gamemode - Is Invulnerable")
@Description({
        "Returns true if the gamemode is invulnerable.",
        "## Requires 1.20.6+"
})
@Examples({
        "if gamemode of player is not invulnerable:",
        "\tkill player"
})
@Since("2.7")
public class CondIsInvulnerable extends PropertyCondition<GameMode> {

    static {
        if (Skript.methodExists(GameMode.class, "isInvulnerable")) {
            register(CondIsInvulnerable.class,
                    "inv(ulnerable|incible)", "gamemodes"
            );
        }
    }

    @Override
    public boolean check(GameMode gameMode) {
        return gameMode.isInvulnerable();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "invulnerable";
    }
}
