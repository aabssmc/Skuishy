package lol.aabss.skuishy.elements.entities.expressions.multiple;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.jetbrains.annotations.Nullable;

@Name("Fox/Wolf - Interested")
@Description("Gets/sets the fox or wolf interested state.")
@Examples({
        "set fox interested state of {_fox} to true"
})
@Since("2.8")
public class ExprInterested extends EntityExpression<LivingEntity, Boolean> {

    static {
        register(ExprInterested.class, Boolean.class, "[fox|wolf] interested [mode|state]", "entities");
    }

    @Override
    public Boolean get(LivingEntity entity) {
        if (entity instanceof Fox) {
            return ((Fox) entity).isInterested();
        } else if (entity instanceof Wolf) {
            return ((Wolf) entity).isInterested();
        }
        return false;
    }

    @Override
    public void change(LivingEntity entity, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof Fox) {
                ((Fox) entity).setInterested(aBoolean);
            } else if (entity instanceof Wolf) {
                ((Wolf) entity).setInterested(aBoolean);
            }
        }
    }
}