package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Slime;
import org.jetbrains.annotations.Nullable;

@Name("Slime - Size")
@Description("Gets/Sets the size of a slime.")
@Examples({
        "set size of last spawned slime to 10"
})
@Since("2.1")
public class ExprSlimeSize extends EntityExpression<Slime, Integer> {

    static {
        register(ExprSlimeSize.class, Integer.class, "slime size", "entities");
    }

    @Override
    public Integer get(Slime slime) {
        return slime.getSize();
    }

    @Override
    public void change(Slime slime, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            slime.setSize(integer);
        }
    }
}
