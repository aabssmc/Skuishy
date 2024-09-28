package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ShulkerBullet;
import org.jetbrains.annotations.Nullable;

@Name("Shulker Bullet - Target")
@Description("Gets/sets the shulker bullet target.")
@Examples({
        "set bullet target of {_shulkerbullet} to target player"
})
@Since("2.8")
public class ExprShulkerBulletTarget extends EntityExpression<ShulkerBullet, Entity> {

    static {
        register(ExprShulkerBulletTarget.class, Entity.class, "[shulker] bullet target", "entities");
    }

    @Override
    public Entity get(ShulkerBullet bullet) {
        return bullet.getTarget();
    }

    @Override
    public void change(ShulkerBullet glowSquid, @Nullable Entity bullet, Changer.ChangeMode mode) {
        if (bullet != null && mode == Changer.ChangeMode.SET) {
            glowSquid.setTarget(bullet);
        }
    }
}