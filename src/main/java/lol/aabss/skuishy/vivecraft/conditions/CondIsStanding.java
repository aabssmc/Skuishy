package lol.aabss.skuishy.vivecraft.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.cjcrafter.vivecraft.VSE;
import com.cjcrafter.vivecraft.VivePlayer;
import org.jetbrains.annotations.NotNull;

@Name("ViveCraft - Is Standing")
@Description("Returns true if the player is standing.")
@Examples({
        "if player is standing:"
})
@Since("1.9")
public class CondIsStanding extends PropertyCondition<VivePlayer> {

    static {
        register(CondIsStanding.class,
                "(standing [up]|stood up)",
                "viveplayers"
        );
    }

    @Override
    public boolean check(VivePlayer vivePlayer) {
        return VSE.isStanding(vivePlayer.player);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "standing";
    }
}
