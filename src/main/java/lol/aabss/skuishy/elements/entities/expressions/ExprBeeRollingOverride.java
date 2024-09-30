package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import net.kyori.adventure.util.TriState;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Rolling Override")
@Description("Gets/sets the rolling override state of a bee.")
@Examples({
        "set rolling override of {_bee} to true"
})
@Since("2.8")
public class ExprBeeRollingOverride extends EntityExpression<Bee, Boolean> {

    static {
        register(ExprBeeRollingOverride.class, Boolean.class, "[bee] rolling override [state|mode]", "entities");
    }

    @Override
    public Boolean get(Bee bee) {
        return bee.getRollingOverride().toBoolean();
    }

    @Override
    public void change(Bee bee, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            bee.setRollingOverride(TriState.byBoolean(aBoolean));
        }
    }
}
