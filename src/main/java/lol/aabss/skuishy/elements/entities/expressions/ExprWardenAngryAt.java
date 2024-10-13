package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Warden;


@Name("Warden - Entity Angry At")
@Description("Gets the angry at entity of a warden.")
@Examples({
        "send angry at of {_warden}"
})
@Since("2.8")
public class ExprWardenAngryAt extends EntityExpression<Warden, LivingEntity> {

    static {
        register(ExprWardenAngryAt.class, LivingEntity.class, "[entity] angry at", "entities");
    }

    @Override
    public LivingEntity get(Warden warden) {
        return warden.getEntityAngryAt();
    }

}
