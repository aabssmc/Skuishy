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

@Name("Zombie Villager - Profession")
@Description("Gets/sets the profession of a zombie villager.")
@Examples({
        "set profession of {_zombieVillager} to farmer"
})
@Since("2.8")
public class ExprZombieVillagerProfession extends EntityExpression<ZombieVillager, KeyedToEnum.VillagerProfession> {

    static {
        register(ExprZombieVillagerProfession.class, KeyedToEnum.VillagerProfession.class, "zombie[ ]villager profession", "entities");
    }

    @Override
    public KeyedToEnum.VillagerProfession get(ZombieVillager zombieVillager) {
        return KeyedToEnum.VillagerProfession.fromBukkit(zombieVillager.getVillagerProfession());
    }

    @Override
    public void change(ZombieVillager zombieVillager, @Nullable KeyedToEnum.VillagerProfession profession, Changer.ChangeMode mode) {
        if (profession != null && mode == Changer.ChangeMode.SET) {
            zombieVillager.setVillagerProfession(profession.toBukkit());
        }
    }
}
