package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.ZombieVillager;

import javax.annotation.Nullable;

@Name("Zombie Villager - Conversion Player")
@Description("Gets/sets the player responsible for converting the zombie villager into a villager.")
@Examples({
        "set conversion player of {_zombieVillager} to player"
})
@Since("2.8")
public class ExprZombieVillagerConversionPlayer extends EntityExpression<ZombieVillager, OfflinePlayer> {

    static {
        register(ExprZombieVillagerConversionPlayer.class, OfflinePlayer.class, "[zombie[ ]villager] conversion player", "entities");
    }

    @Override
    public OfflinePlayer get(ZombieVillager zombieVillager) {
        return zombieVillager.getConversionPlayer();
    }

    @Override
    public void change(ZombieVillager zombieVillager, @Nullable OfflinePlayer player, Changer.ChangeMode mode) {
        if (player != null && mode == Changer.ChangeMode.SET) {
            zombieVillager.setConversionPlayer(player);
        }
    }
}
