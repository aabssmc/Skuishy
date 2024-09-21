package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Hoglin;
import org.jetbrains.annotations.Nullable;

@Name("Hoglin - Is Able To Be Hunted")
@Description("Gets/sets the is able to be hunted of a hoglin.")
@Examples({
        "set able to be hunted of {_hoglin} to true"
})
@Since("2.8")
public class ExprHoglinIsAbleToBeHunted extends EntityExpression<Hoglin, Boolean> {

    static {
        register(ExprHoglinIsAbleToBeHunted.class, Boolean.class, "[hoglin] [is] able to be hunted [state|mode]", "entities");
    }

    @Override
    public Boolean get(Hoglin hoglin) {
        return hoglin.isAbleToBeHunted();
    }

    @Override
    public void change(Hoglin hoglin, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            hoglin.setIsAbleToBeHunted(aBoolean);
        }
    }
}

