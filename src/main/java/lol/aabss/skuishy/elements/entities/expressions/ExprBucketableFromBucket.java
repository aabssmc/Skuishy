package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import io.papermc.paper.entity.Bucketable;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.jetbrains.annotations.Nullable;

@Name("Bucketable - From Bucket State")
@Description("Gets the from bucket state of a bucketable.")
@Examples({
        "set from bucket state of {_fish} to false"
})
@Since("2.9")
public class ExprBucketableFromBucket extends EntityExpression<Bucketable, Boolean> {

    static {
        register(ExprBucketableFromBucket.class, Boolean.class, "[bucketable] from bucket [state|mode]", "entities");
    }

    @Override
    public Boolean get(Bucketable bucketable) {
        return bucketable.isFromBucket();
    }

    @Override
    public void change(Bucketable bucketable, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            bucketable.setFromBucket(aBoolean);
        }
    }
}
