package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import io.papermc.paper.entity.Bucketable;
import lol.aabss.skuishy.other.skript.EntityCondition;

@Name("Bucketable - Is From Bucket")
@Description("True if the bucketale is from bucket.")
@Examples({
        "if {_bucketable} is from bucket:",
        "\tset from bucket state of {_bucketable} to false"
})
@Since("2.9")
public class CondIsBucketableFromBucket extends EntityCondition<Bucketable> {

    static {
        register(CondIsBucketableFromBucket.class, "[bucketable] from bucket", "entities");
    }

    @Override
    protected boolean run(Bucketable bucketable) {
        return bucketable.isFromBucket();
    }
}
