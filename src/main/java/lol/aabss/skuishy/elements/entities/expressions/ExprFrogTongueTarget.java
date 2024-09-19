package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Frog;
import org.jetbrains.annotations.Nullable;

@Name("Frog - Tongue Target")
@Description("Gets/sets the tongue target of a frog.")
@Examples({
        "set tongue target of {_frog} to target entity"
})
@Since("2.8")
public class ExprFrogTongueTarget extends EntityExpression<Frog, Entity> {

    static {
        register(ExprFrogTongueTarget.class, Entity.class, "[frog] tongue target", "entities");
    }

    @Override
    public Entity get(Frog frog) {
        return frog.getTongueTarget();
    }

    @Override
    public void change(Frog frog, @Nullable Entity entity, Changer.ChangeMode mode) {
        if (entity != null && mode == Changer.ChangeMode.SET) {
            frog.setTongueTarget(entity);
        }
    }
}
