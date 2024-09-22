package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Llama;
import org.jetbrains.annotations.Nullable;

@Name("Llama - Strength")
@Description("Gets/sets the strength of a llama.")
@Examples({
        "set llama strength of {_llama} to 100"
})
@Since("2.8")
public class ExprLlamaStrength extends EntityExpression<Llama, Integer> {

    static {
        register(ExprLlamaStrength.class, Integer.class, "llama strength", "entities");
    }

    @Override
    public Integer get(Llama llama) {
        return llama.getStrength();
    }

    @Override
    public void change(Llama llama, @Nullable Integer integer, Changer.ChangeMode mode) {
        if (integer != null && mode == Changer.ChangeMode.SET) {
            llama.setStrength(integer);
        }
    }
}