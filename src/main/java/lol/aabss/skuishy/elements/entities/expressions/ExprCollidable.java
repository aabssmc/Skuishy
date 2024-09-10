package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

@Name("Living Entity - Collidable")
@Description("Sets/Gets the collidable state of a living entity.")
@Examples({
        "set collidable state of target entity to true"
})
@Since("2.1")
public class ExprCollidable extends EntityExpression<LivingEntity, Boolean> {

    static {
        register(ExprCollidable.class, Boolean.class, "collidab(le|ility) [state|mode]", "livingentities");
    }

    @Override
    public Boolean get(LivingEntity livingEntity) {
        return livingEntity.isCollidable();
    }

    @Override
    public void change(LivingEntity livingEntity, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            livingEntity.setCollidable(aBoolean);
        }
    }
}
