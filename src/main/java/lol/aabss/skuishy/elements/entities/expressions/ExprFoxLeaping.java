package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Fox;
import org.jetbrains.annotations.Nullable;

@Name("Fox - Leaping")
@Description("Gets/sets the fox leaping state.")
@Examples({
        "set fox leaping state of {_fox} to true"
})
@Since("2.8")
public class ExprFoxLeaping extends EntityExpression<Fox, Boolean> {

    static {
        register(ExprFoxLeaping.class, Boolean.class, "[fox] leaping [mode|state]", "entities");
    }

    @Override
    public Boolean get(Fox fox) {
        return fox.isLeaping();
    }

    @Override
    public void change(Fox fox, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            fox.setLeaping(aBoolean);
        }
    }
}