package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

@Name("Shulker Bullet - Target Delta")
@Description("Gets/sets the shulker bullet target delta.")
@Examples({
        "set bullet target delta of {_shulkerbullet} to {_vector}"
})
@Since("2.8")
public class ExprShulkerBulletTargetDelta extends EntityExpression<ShulkerBullet, Vector> {

    static {
        register(ExprShulkerBulletTargetDelta.class, Vector.class, "[shulker[ ]bullet] target delta", "entities");
    }

    @Override
    public Vector get(ShulkerBullet bullet) {
        return bullet.getTargetDelta();
    }

    @Override
    public void change(ShulkerBullet glowSquid, @Nullable Vector bullet, Changer.ChangeMode mode) {
        if (bullet != null && mode == Changer.ChangeMode.SET) {
            glowSquid.setTargetDelta(bullet);
        }
    }
}