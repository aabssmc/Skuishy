package lol.aabss.skuishy.elements.vulcan.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Name("Vulcan - Is Frozen")
@Description("Returns true if the player is frozen.")
@Examples({
        "if player is frozen:"
})
@Since("2.1")
public class CondIsVulcanFrozen extends PropertyCondition<Player> {

    static {
        register(CondIsVulcanFrozen.class,
                PropertyType.BE,
                "vulcan frozen",
                "players"
        );
    }

    @Override
    public boolean check(Player player) {
        return VulcanAPI.Factory.getApi().isFrozen(player);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "vulcan frozen";
    }
}
