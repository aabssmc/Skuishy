package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Skeleton - Powdered Snow Time")
@Description("Gets the powdered snow time.")
@Examples({
        "set {_time} to powdered snow time of {_skeleton}"
})
@Since("2.8")
public class ExprPowderedSnowTime extends SimplePropertyExpression<Entity, Integer> {

    static {
        register(ExprPowderedSnowTime.class, Integer.class, "[in] powdered snow time", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "powdered snow time";
    }

    @Override
    public @Nullable Integer convert(Entity entity) {
        if (entity instanceof Skeleton) {
            return ((Skeleton) entity).inPowderedSnowTime();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
