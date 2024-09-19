package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Cat;
import org.jetbrains.annotations.Nullable;

@Name("Cat - Head Up")
@Description("Gets/sets the head up state of a cat.")
@Examples({
        "set head up of {_cat} to true"
})
@Since("2.8")
public class ExprCatHeadUp extends EntityExpression<Cat, Boolean> {

    static {
        register(ExprCatHeadUp.class, Boolean.class, "[cat] head up [state|mode]", "entities");
    }

    @Override
    public Boolean get(Cat cat) {
        return cat.isHeadUp();
    }

    @Override
    public void change(Cat cat, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            cat.setHeadUp(aBoolean);
        }
    }
}