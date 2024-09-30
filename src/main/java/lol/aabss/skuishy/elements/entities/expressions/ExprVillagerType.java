package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.KeyedToEnum;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

import javax.annotation.Nullable;

@Name("Villager - Villager Type")
@Description("Gets/sets the type of a villager.")
@Examples({
        "set villager type of {_zombieVillager} to desert"
})
@Since("2.8")
public class ExprVillagerType extends EntityExpression<Entity, KeyedToEnum.VillagerType> {

    static {
        register(ExprVillagerType.class, KeyedToEnum.VillagerType.class, "[zombie[ ]]villager type", "entities");
    }

    @Override
    public KeyedToEnum.VillagerType get(Entity entity) {
        if (entity instanceof Villager) {
            return KeyedToEnum.VillagerType.fromBukkit(((Villager) entity).getVillagerType());
        } else if (entity instanceof ZombieVillager) {
            return KeyedToEnum.VillagerType.fromBukkit(((ZombieVillager) entity).getVillagerType());
        }
        return null;
    }

    @Override
    public void change(Entity entity, @Nullable KeyedToEnum.VillagerType type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof Villager) {
                ((Villager) entity).setVillagerType(type.toBukkit());
            } else if (entity instanceof ZombieVillager) {
                ((ZombieVillager) entity).setVillagerType(type.toBukkit());
            }
        }
    }
}
