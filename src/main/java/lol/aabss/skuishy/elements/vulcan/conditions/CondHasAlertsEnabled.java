package lol.aabss.skuishy.elements.vulcan.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Name("Vulcan - Has Alerts Enabled")
@Description("Returns true if the player has the vulcan alerts enabled.")
@Examples({
        "if player has vulcan alerts enabled:"
})
@Since("2.1")
public class CondHasAlertsEnabled extends PropertyCondition<Player> {

    static {
        register(CondHasAlertsEnabled.class,
                PropertyType.HAVE,
                "[vulcan] alerts enabled",
                "players"
        );
    }

    @Override
    public boolean check(Player player) {
        return VulcanAPI.Factory.getApi().hasAlertsEnabled(player);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "vulcan alerts enabled";
    }
}
