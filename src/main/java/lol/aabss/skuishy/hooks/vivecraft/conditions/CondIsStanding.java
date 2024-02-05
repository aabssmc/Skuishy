package lol.aabss.skuishy.hooks.vivecraft.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.vivecraft.VSE;

@Name("ViveCraft - Is Standing")
@Description("Returns true if the player is standing.")
@Examples({
        "if player is standing:"
})
@Since("1.9")
public class CondIsStanding extends PropertyCondition<Player> {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
            register(CondIsStanding.class,
                    "(standing [up]|stood up)",
                    "viveplayers/players"
            );
        }
    }

    @Override
    public boolean check(Player vivePlayer) {
        if (VSE.vivePlayers.containsKey(vivePlayer.getUniqueId())) {
            return VSE.isStanding(vivePlayer);
        }
        Skript.error("Player is not a ViveCraft player!");
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "standing";
    }
}
