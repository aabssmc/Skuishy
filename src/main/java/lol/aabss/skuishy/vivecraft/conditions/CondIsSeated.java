package lol.aabss.skuishy.vivecraft.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.cjcrafter.vivecraft.VSE;
import com.cjcrafter.vivecraft.VivePlayer;
import org.jetbrains.annotations.NotNull;

@Name("ViveCraft - Is Seated")
@Description("Returns true if the player is sitting.")
@Examples({
        "if player is sitting:"
})
@Since("1.9")
public class CondIsSeated extends PropertyCondition<VivePlayer> {

    static {
        register(CondIsSeated.class,
                "(seated|sitting)",
                "viveplayers"
        );
    }

    @Override
    public boolean check(VivePlayer vivePlayer) {
        return VSE.isSeated(vivePlayer.player);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "seated";
    }
}
