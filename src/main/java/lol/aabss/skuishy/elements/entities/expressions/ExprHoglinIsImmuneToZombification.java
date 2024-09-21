package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Hoglin;
import org.jetbrains.annotations.Nullable;

@Name("Hoglin - Is Immune To Zombification")
@Description("Gets/sets the is immune to zombification state of a hoglin.")
@Examples({
        "set immune to zombifying of {_hoglin} to true"
})
@Since("2.8")
public class ExprHoglinIsImmuneToZombification extends EntityExpression<Hoglin, Boolean> {

    static {
        register(ExprHoglinIsImmuneToZombification.class, Boolean.class, "[hoglin] [is] immune to zombif(ying|ication) [state|mode]", "entities");
    }

    @Override
    public Boolean get(Hoglin hoglin) {
        return hoglin.isImmuneToZombification();
    }

    @Override
    public void change(Hoglin hoglin, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            hoglin.setImmuneToZombification(aBoolean);
        }
    }
}

