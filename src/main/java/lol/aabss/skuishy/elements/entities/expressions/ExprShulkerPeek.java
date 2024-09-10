package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Shulker;
import org.jetbrains.annotations.Nullable;

@Name("Shulker - Peek State")
@Description({"Gets/sets the peek state of a shulker.", "Has to be between 0.0 and 1.0"})
@Examples({
        "set shulker peek of {_shulker} to 0.95"
})
@Since("2.8")
public class ExprShulkerPeek extends EntityExpression<Shulker, Float> {

    static {
        register(ExprShulkerPeek.class, Float.class, "shulker peek [state]", "entities");
    }

    @Override
    public Float get(Shulker shulker) {
        return shulker.getPeek();
    }

    @Override
    public void change(Shulker shulker, @Nullable Float aFloat, Changer.ChangeMode mode) {
        if (aFloat != null && mode == Changer.ChangeMode.SET) {
            shulker.setPeek(aFloat);
        }
    }
}