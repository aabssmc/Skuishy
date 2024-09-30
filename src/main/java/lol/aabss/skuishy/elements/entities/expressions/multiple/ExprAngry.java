package lol.aabss.skuishy.elements.entities.expressions.multiple;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Wolf;
import org.jetbrains.annotations.Nullable;

@Name("PigZombie/Wolf - Angry")
@Description("Gets/sets the pig zombie or wolf angry state.")
@Examples({
        "set wolf angry state of {_wolf} to true"
})
@Since("2.8")
public class ExprAngry extends EntityExpression<LivingEntity, Boolean> {

    static {
        register(ExprAngry.class, Boolean.class, "[pig[ ]zombie|wolf] interested [mode|state]", "entities");
    }

    @Override
    public Boolean get(LivingEntity entity) {
        if (entity instanceof PigZombie) {
            return ((PigZombie) entity).isAngry();
        } else if (entity instanceof Wolf) {
            return ((Wolf) entity).isAngry();
        }
        return false;
    }

    @Override
    public void change(LivingEntity entity, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof PigZombie) {
                ((PigZombie) entity).setAngry(aBoolean);
            } else if (entity instanceof Wolf) {
                ((Wolf) entity).setAngry(aBoolean);
            }
        }
    }
}