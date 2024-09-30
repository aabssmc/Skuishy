package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Wither;

import javax.annotation.Nullable;

@Name("Wither - Portal Travel")
@Description("Gets/sets whether travel through portals state of a wither.")
@Examples({
        "set can travel through portals of {_wither} to true"
})
@Since("2.8")
public class ExprWitherPortalTravel extends EntityExpression<Wither, Boolean> {

    static {
        register(ExprWitherPortalTravel.class, Boolean.class, "[wither] [can] travel through portals [state|mode]", "entities");
    }

    @Override
    public Boolean get(Wither wither) {
        return wither.canTravelThroughPortals();
    }

    @Override
    public void change(Wither wither, @Nullable Boolean bool, Changer.ChangeMode mode) {
        if (bool != null && mode == Changer.ChangeMode.SET) {
            wither.setCanTravelThroughPortals(bool);
        }
    }
}
