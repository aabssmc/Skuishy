package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;

@Name("Llama - Caravan Part")
@Description("Gets/sets the strength of a llama.")
@Examples({
        "set {_head} to llama caravan head of {_llama}"
})
@Since("2.8")
public class ExprLlamaCaravan extends EntityExpression<Llama, Entity> {

    static {
        register(ExprLlamaCaravan.class, Entity.class, "llama caravan (:head|tail)", "entities");
    }

    @Override
    public Entity get(Llama llama) {
        return tags.contains("head") ? llama.getCaravanHead() : llama.getCaravanTail();
    }
}
