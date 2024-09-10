package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Display Block Offset")
@Description("Gets/sets the display block offset of a minecart.")
@Examples({
        "set display block offset of {_minecart} to 20"
})
@Since("2.8")
public class ExprMinecartDisplayBlockOffset extends EntityExpression<Minecart, Integer> {

    static {
        register(ExprMinecartDisplayBlockOffset.class, Integer.class, "[minecart] display block offset", "entities");
    }

    @Override
    public Integer get(Minecart minecart) {
        return minecart.getDisplayBlockOffset();
    }

    @Override
    public void change(Minecart minecart, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            minecart.setDisplayBlockOffset(integer);
        }
    }
}