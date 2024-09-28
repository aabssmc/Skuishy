package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Snowman;
import org.jetbrains.annotations.Nullable;

@Name("Snowman - Derp")
@Description("Gets/sets the derp state of the snowman.")
@Examples({
        "set derp state of {_snowman} to true"
})
@Since("2.8")
public class ExprSnowmanDerp extends EntityExpression<Snowman, Boolean> {

    static {
        register(ExprSnowmanDerp.class, Boolean.class, "[snowman] derp [state|mode]", "entities");
    }

    @Override
    public Boolean get(Snowman snowman) {
        return snowman.isDerp();
    }

    @Override
    public void change(Snowman snowman, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            snowman.setDerp(aBoolean);
        }
    }
}
