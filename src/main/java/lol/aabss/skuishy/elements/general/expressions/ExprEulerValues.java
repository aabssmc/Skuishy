package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Euler Angle - Values")
@Description("Gets/sets/removes/adds/resets the values (x, y and z) from an euler angle.")
@Examples({
        "set {_angle} to eulerangle(10, 20, 30)",
        "remove 10 from euler-y of {_angle}",
        "remove 20 from euler-z of {_angle}"
})
@Since("2.8")
public class ExprEulerValues extends SimplePropertyExpression<EulerAngle, Double> {

    static {
        register(ExprEulerValues.class, Double.class, "euler( |-)(:(x|y|z))", "eulerangles");
    }

    private String coord;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.hasTag("x")) {
            coord = "x";
        } else if (parseResult.hasTag("y")) {
            coord = "y";
        } else if (parseResult.hasTag("z")) {
            coord = "z";
        }
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "euler values";
    }

    @Override
    public @Nullable Double convert(EulerAngle eulerAngle) {
        return switch (coord) {
            case "x" -> eulerAngle.getX();
            case "y" -> eulerAngle.getY();
            case "z" -> eulerAngle.getZ();
            default -> null;
        };
    }

    @Override
    public @NotNull Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(Double.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        for (EulerAngle eulerAngle : getExpr().getArray(e)) {
            if (mode == Changer.ChangeMode.SET) {
                switch (coord) {
                    case "x" -> eulerAngle.setX((Double) delta[0]);
                    case "y" -> eulerAngle.setY((Double) delta[0]);
                    case "z" -> eulerAngle.setZ((Double) delta[0]);
                }
            } else if (mode == Changer.ChangeMode.ADD) {
                switch (coord) {
                    case "x" -> eulerAngle.setX(eulerAngle.getX()+(Double) delta[0]);
                    case "y" -> eulerAngle.setY(eulerAngle.getY()+(Double) delta[0]);
                    case "z" -> eulerAngle.setZ(eulerAngle.getZ()+(Double) delta[0]);
                }
            } else if (mode == Changer.ChangeMode.REMOVE) {
                switch (coord) {
                    case "x" -> eulerAngle.setX(eulerAngle.getX()-(Double) delta[0]);
                    case "y" -> eulerAngle.setY(eulerAngle.getY()-(Double) delta[0]);
                    case "z" -> eulerAngle.setZ(eulerAngle.getZ()-(Double) delta[0]);
                }
            } else {
                switch (coord) {
                    case "x" -> eulerAngle.setX(0);
                    case "y" -> eulerAngle.setY(0);
                    case "z" -> eulerAngle.setZ(0);
                }
            }
        }
    }
}
