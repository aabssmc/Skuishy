package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import io.papermc.paper.entity.Bucketable;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.inventory.ItemStack;

@Name("Bucketable - Base Bucket Item")
@Description("Gets the base bucket item of a bucketable.")
@Examples({
        "send pickup sound of {_pufferfish}"
})
@Since("2.9")
public class ExprBucketableBaseBucketItem extends EntityExpression<Bucketable, ItemStack> {

    static {
        register(ExprBucketableBaseBucketItem.class, ItemStack.class, "[bucketable] base bucket item", "entities");
    }

    @Override
    public ItemStack get(Bucketable bucketable) {
        return bucketable.getBaseBucketItem();
    }
}
