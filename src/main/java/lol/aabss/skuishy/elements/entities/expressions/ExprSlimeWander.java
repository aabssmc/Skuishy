package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Slime;
import org.jetbrains.annotations.Nullable;

@Name("Slime - Wander")
@Description("Gets/sets the wander state of the slime.")
@Examples({
        "set slime wander state of {_slime} to true"
})
@Since("2.8")
public class ExprSlimeWander extends EntityExpression<Slime, Boolean> {

    static {
        register(ExprSlimeWander.class, Boolean.class, "slime wander [state|mode]", "entities");
    }

    @Override
    public Boolean get(Slime slime) {
        return slime.canWander();
    }

    @Override
    public void change(Slime slime, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            slime.setWander(aBoolean);
        }
    }
}
