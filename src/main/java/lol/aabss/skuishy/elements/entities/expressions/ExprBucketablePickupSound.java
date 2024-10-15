package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import io.papermc.paper.entity.Bucketable;
import lol.aabss.skuishy.other.skript.EntityExpression;

@Name("Bucketable - Pickup Sound")
@Description("Gets the pickup sound of a bucketable.")
@Examples({
        "send pickup sound of {_axolotl}"
})
@Since("2.9")
public class ExprBucketablePickupSound extends EntityExpression<Bucketable, String> {

    static {
        register(ExprBucketablePickupSound.class, String.class, "[bucketable] bucket pickup sound", "entities");
    }

    @Override
    public String get(Bucketable bucketable) {
        return bucketable.getPickupSound().key().value();
    }
}
