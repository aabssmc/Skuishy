package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Ocelot;
import org.jetbrains.annotations.Nullable;

@Name("Ocelot - Trusting")
@Description("Gets/sets the trusting state of a ocelot.")
@Examples({
        "set trusting of {_ocelot} to 100"
})
@Since("2.8")
public class ExprOcelotTrusting extends EntityExpression<Ocelot, Boolean> {

    static {
        register(ExprOcelotTrusting.class, Boolean.class, "[ocelot] trusting [state|mode]", "entities");
    }

    @Override
    public Boolean get(Ocelot ocelot) {
        return ocelot.isTrusting();
    }

    @Override
    public void change(Ocelot ocelot, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            ocelot.setTrusting(aBoolean);
        }
    }
}