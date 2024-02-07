package lol.aabss.skuishy.hooks.vulcan.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static lol.aabss.skuishy.hooks.vulcan.Vulcan.vulcan;

@Name("Vulcan - Is Frozen")
@Description("Returns true if the player is frozen.")
@Examples({
        "if player is frozen:"
})
@Since("2.1")
public class CondIsFrozen extends PropertyCondition<Player> {

    static {
        register(CondIsFrozen.class,
                PropertyType.BE,
                "[vulcan] frozen",
                "players"
        );
    }

    @Override
    public boolean check(Player player) {
        return vulcan().isFrozen(player);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "vulcan frozen";
    }
}
