package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Llama;
import org.jetbrains.annotations.Nullable;

@Name("Llama - Variant")
@Description("Gets/sets the variant of an llama.")
@Examples({
        "set llama variant of {_llama} to salt pepper"
})
@Since("2.8")
public class ExprLlamaVariant extends EntityExpression<Llama, Llama.Color> {

    static {
        register(ExprLlamaVariant.class, Llama.Color.class, "llama (variant|type|color)", "entities");
    }

    @Override
    public Llama.Color get(Llama llama) {
        return llama.getColor();
    }

    @Override
    public void change(Llama llama, Llama.@Nullable Color color, Changer.ChangeMode mode) {
        if (color != null && mode == Changer.ChangeMode.SET) {
            llama.setColor(color);
        }
    }
}