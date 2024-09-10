package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Allay;
import org.jetbrains.annotations.Nullable;

@Name("Allay - Can Duplicate")
@Description("Gets/sets the can duplicate state of an allay.")
@Examples({
        "set can duplicate state of {_allay} to true"
})
@Since("2.8")
public class ExprAllayCanDuplicate extends EntityExpression<Allay, Boolean> {

    static {
        register(ExprAllayCanDuplicate.class, Boolean.class, "[allay] can duplicate [state|mode]", "entities");
    }

    @Override
    public Boolean get(Allay allay) {
        return allay.canDuplicate();
    }

    @Override
    public void change(Allay allay, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            allay.setCanDuplicate(aBoolean);
        }
    }

}