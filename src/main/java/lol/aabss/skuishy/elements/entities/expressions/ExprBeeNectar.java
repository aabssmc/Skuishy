package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Has Nectar State")
@Description("Gets/sets whether a bee has nectar.")
@Examples({
        "set nectar state of {_bee} to true"
})
@Since("2.8")
public class ExprBeeNectar extends EntityExpression<Bee, Boolean> {

    static {
        register(ExprBeeNectar.class, Boolean.class, "[bee] has nectar [state|mode]", "entities");
    }

    @Override
    public Boolean get(Bee bee) {
        return bee.hasNectar();
    }

    @Override
    public void change(Bee bee, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            bee.setHasNectar(aBoolean);
        }
    }
}
