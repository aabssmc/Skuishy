package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.*;
import org.jetbrains.annotations.Nullable;

@Name("Evoker Fangs - Owner")
@Description("Gets/sets the owner of evoker fangs.")
@Examples({
        "set owner of {_evokerfangs} to {_evoker}"
})
@Since("2.8")
public class ExprEvokerFangsOwner extends EntityExpression<EvokerFangs, LivingEntity> {
    static {
        register(ExprEvokerFangsOwner.class, LivingEntity.class, "[evoker] fangs owner", "entities");
    }

    @Override
    public LivingEntity get(EvokerFangs evokerFangs) {
        return evokerFangs.getOwner();
    }

    @Override
    public void change(EvokerFangs evokerFangs, @Nullable LivingEntity entity, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && entity != null) {
            evokerFangs.setOwner(entity);
        }
    }
}
