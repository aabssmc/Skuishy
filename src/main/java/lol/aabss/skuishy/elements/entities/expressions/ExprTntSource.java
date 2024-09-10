package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.jetbrains.annotations.Nullable;

@Name("Primed TNT - Source")
@Description("Gets/sets the source of a primed tnt.")
@Examples({
        "set tnt source of {_tnt} to player"
})
@Since("2.8")
public class ExprTntSource extends EntityExpression<TNTPrimed, Entity> {

    static {
        register(ExprTntSource.class, Entity.class, "[primed[-| ]]tnt source", "entities");
    }

    @Override
    public Entity get(TNTPrimed tntPrimed) {
        return tntPrimed.getSource();
    }

    @Override
    public void change(TNTPrimed tntPrimed, @Nullable Entity entity, Changer.ChangeMode mode) {
        if (entity != null && mode == Changer.ChangeMode.SET) {
            tntPrimed.setSource(entity);
        }
    }
}
