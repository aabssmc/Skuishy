package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ShulkerBullet;
import org.jetbrains.annotations.Nullable;

@Name("Shulker Bullet - Flight Steps")
@Description("Gets/sets the shulker bullet flight steps.")
@Examples({
        "set flight steps of {_shulkerbullet} to 1000"
})
@Since("2.8")
public class ExprShulkerBulletFlightSteps extends EntityExpression<ShulkerBullet, Integer> {

    static {
        register(ExprShulkerBulletFlightSteps.class, Integer.class, "[shulker[ ]bullet] flight steps", "entities");
    }

    @Override
    public Integer get(ShulkerBullet shulkerBullet) {
        return shulkerBullet.getFlightSteps();
    }

    @Override
    public void change(ShulkerBullet shulkerBullet, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            shulkerBullet.setFlightSteps(integer);
        }
    }
}