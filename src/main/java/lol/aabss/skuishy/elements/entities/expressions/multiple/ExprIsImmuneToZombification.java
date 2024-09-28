package lol.aabss.skuishy.elements.entities.expressions.multiple;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PiglinAbstract;
import org.jetbrains.annotations.Nullable;

@Name("Hoglin/Piglin - Is Immune To Zombification")
@Description("Gets/sets the is immune to zombification state of a hoglin or piglin.")
@Examples({
        "set immune to zombifying of {_hoglin} to true"
})
@Since("2.8")
public class ExprIsImmuneToZombification extends EntityExpression<LivingEntity, Boolean> {

    static {
        register(ExprIsImmuneToZombification.class, Boolean.class, "[hoglin|piglin] [is] immune to zombif(ying|ication) [state|mode]", "entities");
    }

    @Override
    public Boolean get(LivingEntity entity) {
        if (entity instanceof Hoglin) {
            return ((Hoglin) entity).isImmuneToZombification();
        } else if (entity instanceof PiglinAbstract) {
            return ((PiglinAbstract) entity).isImmuneToZombification();
        }
        return null;
    }

    @Override
    public void change(LivingEntity entity, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof Hoglin) {
                ((Hoglin) entity).setImmuneToZombification(aBoolean);
            } else if (entity instanceof PiglinAbstract) {
                ((PiglinAbstract) entity).setImmuneToZombification(aBoolean);
            }
        }
    }
}

