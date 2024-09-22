package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Name("Lightning - Causing Player")
@Description("Gets/sets the causing player of a lightning.")
@Examples({
        "set causing player of {_lightning} to target player"
})
@Since("2.8")
public class ExprLightningCausingPlayer extends EntityExpression<LightningStrike, Player> {

    static {
        register(ExprLightningCausingPlayer.class, Player.class, "[lightning] causing player", "entities");
    }

    @Override
    public Player get(LightningStrike lightning) {
        return lightning.getCausingPlayer();
    }

    @Override
    public void change(LightningStrike lightning, @Nullable Player player, Changer.ChangeMode mode) {
        if (player != null && mode == Changer.ChangeMode.SET) {
            lightning.setCausingPlayer(player);
        }
    }
}