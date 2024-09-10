package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Fox;
import org.jetbrains.annotations.Nullable;

@Name("Fox - Defending")
@Description("Gets/sets the fox defending state.")
@Examples({
        "set fox defending state of {_fox} to true"
})
@Since("2.8")
public class ExprFoxDefending extends EntityExpression<Fox, Boolean> {

    static {
        register(ExprFoxDefending.class, Boolean.class, "[fox] defending [mode|state]", "entities");
    }

    @Override
    public Boolean get(Fox fox) {
        return fox.isDefending();
    }

    @Override
    public void change(Fox fox, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            fox.setDefending(aBoolean);
        }
    }

}