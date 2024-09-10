package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Sheep;
import org.jetbrains.annotations.Nullable;

@Name("Evoker - Wololo Target")
@Description("Gets/sets the wololo target.")
@Examples({
        "set wololo target of {_evoker} to {_sheep}"
})
@Since("2.8")
public class ExprEvokerWololoTarget extends EntityExpression<Evoker, Entity> {

    static {
        register(ExprEvokerWololoTarget.class, Entity.class, "[evoker] wololo target", "entities");
    }

    @Override
    public Entity get(Evoker evoker) {
        return evoker.getWololoTarget();
    }

    @Override
    public void change(Evoker evoker, @Nullable Entity entity, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && entity instanceof Sheep) {
            evoker.setWololoTarget((Sheep) entity);
        }
    }

}