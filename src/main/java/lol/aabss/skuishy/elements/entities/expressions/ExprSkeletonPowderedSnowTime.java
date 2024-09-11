package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Skeleton;

@Name("Skeleton - Powdered Snow Time")
@Description("Gets the powdered snow time.")
@Examples({
        "set {_time} to powdered snow time of {_skeleton}"
})
@Since("2.8")
public class ExprSkeletonPowderedSnowTime extends EntityExpression<Skeleton, Integer> {

    static {
        register(ExprSkeletonPowderedSnowTime.class, Integer.class, "[in] powdered snow time", "entities");
    }

    @Override
    public Integer get(Skeleton skeleton) {
        return skeleton.inPowderedSnowTime();
    }

}
