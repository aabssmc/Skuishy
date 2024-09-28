package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Strider;
import org.jetbrains.annotations.Nullable;

@Name("Strider - Shivering")
@Description("Gets/sets the shivering state of the strider.")
@Examples({
        "set shivering state of {_strider} to true"
})
@Since("2.8")
public class ExprStriderShivering extends EntityExpression<Strider, Boolean> {

    static {
        register(ExprStriderShivering.class, Boolean.class, "[strider] shivering [state|mode]", "entities");
    }

    @Override
    public Boolean get(Strider strider) {
        return strider.isShivering();
    }

    @Override
    public void change(Strider strider, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            strider.setShivering(aBoolean);
        }
    }
}
