package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.KeyedToEnum;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ZombieVillager;

import javax.annotation.Nullable;

@Name("Zombie Villager - Villager Type")
@Description("Gets/sets the type of a zombie villager.")
@Examples({
        "set villager type of {_zombieVillager} to desert"
})
@Since("2.8")
public class ExprZombieVillagerType extends EntityExpression<ZombieVillager, KeyedToEnum.VillagerType> {

    static {
        register(ExprZombieVillagerType.class, KeyedToEnum.VillagerType.class, "zombie[ ]villager type", "entities");
    }

    @Override
    public KeyedToEnum.VillagerType get(ZombieVillager zombieVillager) {
        return KeyedToEnum.VillagerType.fromBukkit(zombieVillager.getVillagerType());
    }

    @Override
    public void change(ZombieVillager zombieVillager, @Nullable KeyedToEnum.VillagerType type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            zombieVillager.setVillagerType(type.toBukkit());
        }
    }
}
