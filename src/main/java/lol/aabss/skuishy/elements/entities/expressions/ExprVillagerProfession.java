package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

import javax.annotation.Nullable;

@Name("Villager - Profession")
@Description("Gets/sets the profession of a villager.")
@Examples({
        "set profession of {_zombieVillager} to farmer"
})
@Since("2.8")
public class ExprVillagerProfession extends EntityExpression<Entity, Villager.Profession> {

    static {
        register(ExprVillagerProfession.class, Villager.Profession.class, "[zombie[ ]]villager profession", "entities");
    }

    @Override
    public Villager.Profession get(Entity entity) {
        if (entity instanceof Villager) {
            return ((Villager) entity).getProfession();
        } else if (entity instanceof ZombieVillager) {
            return ((ZombieVillager) entity).getVillagerProfession();
        }
        return null;
    }

    @Override
    public void change(Entity entity, @Nullable Villager.Profession profession, Changer.ChangeMode mode) {
        if (profession != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof Villager) {
                ((Villager) entity).setProfession(profession);
            } else if (entity instanceof ZombieVillager) {
                ((ZombieVillager) entity).setVillagerProfession(profession);
            }
        }
    }
}
