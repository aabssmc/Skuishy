package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Has Stung State")
@Description("Gets/sets whether a bee has stung.")
@Examples({
        "set stung state of {_bee} to true"
})
@Since("2.8")
public class ExprBeeStung extends EntityExpression<Bee, Boolean> {

    static {
        register(ExprBeeStung.class, Boolean.class, "[bee] has stung [state|mode]", "entities");
    }

    @Override
    public Boolean get(Bee bee) {
        return bee.hasStung();
    }

    @Override
    public void change(Bee bee, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            bee.setHasStung(aBoolean);
        }
    }
}
