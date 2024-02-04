package lol.aabss.skuishy.hooks.vivecraft.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.vivecraft.VSE;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Name("ViveCraft - Is Vive Player")
@Description("Returns true if the player is a vivecraft player.")
@Examples({
        "if player is a vivecraft player:"
})
@Since("1.9")
public class CondIsVivePlayer extends PropertyCondition<Player> {

    static {
        register(CondIsVivePlayer.class,
                "[a] vive[craft] player",
                "players");
    }

    @Override
    public boolean check(Player player) {
        return VSE.isVive(player);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "vive player";
    }
}
