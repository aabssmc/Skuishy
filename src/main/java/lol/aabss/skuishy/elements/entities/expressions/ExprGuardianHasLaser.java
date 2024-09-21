package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Guardian;
import org.jetbrains.annotations.Nullable;

@Name("Guardian - Has Laser State")
@Description("Gets/sets the has laser state of a guardian.")
@Examples({
        "set has laser state of {_guardian} to true"
})
@Since("2.8")
public class ExprGuardianHasLaser extends EntityExpression<Guardian, Boolean> {

    static {
        register(ExprGuardianHasLaser.class, Boolean.class, "[[guardian] has laser [state|mode]", "entities");
    }

    @Override
    public Boolean get(Guardian guardian) {
        return guardian.hasLaser();
    }

    @Override
    public void change(Guardian guardian, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            guardian.setLaser(aBoolean);
        }
    }
}

