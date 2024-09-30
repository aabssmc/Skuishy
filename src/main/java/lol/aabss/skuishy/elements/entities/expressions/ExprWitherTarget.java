package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;

import javax.annotation.Nullable;

@Name("Wither - Target")
@Description("Gets/sets the target of a wither.")
@Examples({
        "set target of {_wither} to player"
})
@Since("2.8")
public class ExprWitherTarget extends EntityExpression<Wither, LivingEntity> {

    static {
        register(ExprWitherTarget.class, LivingEntity.class, "[wither] target", "entities");
    }

    @Override
    public LivingEntity get(Wither wither) {
        return wither.getTarget(Wither.Head.CENTER);
    }

    @Override
    public void change(Wither wither, @Nullable LivingEntity target, Changer.ChangeMode mode) {
        if (target != null && mode == Changer.ChangeMode.SET) {
            wither.setTarget(target);
        }
    }
}
