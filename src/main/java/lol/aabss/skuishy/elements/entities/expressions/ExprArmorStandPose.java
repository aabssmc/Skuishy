package lol.aabss.skuishy.elements.entities.expressions;

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
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Armor Stand - Poses")
@Description("Gets/sets the poses of an armor stand.")
@Examples({
        "set {_angle} to head pose of {_armorstand}"
})
@Since("2.8")
public class ExprArmorStandPose extends SimplePropertyExpression<Entity, EulerAngle> {

    static {
        register(ExprArmorStandPose.class, EulerAngle.class,
                "(:(head|torso|right arm|left arm|right leg|left leg)) pose",
                "entities"
        );
    }

    private String part;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        part = parseResult.tags.getFirst();
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "armor stand pose";
    }

    @Override
    public @Nullable EulerAngle convert(Entity entity) {
        if (entity instanceof ArmorStand) {
            return switch (part) {
                case "head" -> ((ArmorStand) entity).getHeadPose();
                case "torso" -> ((ArmorStand) entity).getBodyPose();
                case "right arm" -> ((ArmorStand) entity).getRightArmPose();
                case "left arm" -> ((ArmorStand) entity).getLeftArmPose();
                case "right leg" -> ((ArmorStand) entity).getRightLegPose();
                case "left leg" -> ((ArmorStand) entity).getLeftLegPose();
                default -> null;
            };
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends EulerAngle> getReturnType() {
        return EulerAngle.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(EulerAngle.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        for (Entity entity : getExpr().getArray(e)) {
            if (!(entity instanceof ArmorStand)){
                continue;
            }
            if (mode == Changer.ChangeMode.SET) {
                switch (part) {
                    case "head" -> ((ArmorStand) entity).setHeadPose((EulerAngle) delta[0]);
                    case "torso" -> ((ArmorStand) entity).setBodyPose((EulerAngle) delta[0]);
                    case "right arm" -> ((ArmorStand) entity).setRightArmPose((EulerAngle) delta[0]);
                    case "left arm" -> ((ArmorStand) entity).setLeftArmPose((EulerAngle) delta[0]);
                    case "right leg" -> ((ArmorStand) entity).setRightLegPose((EulerAngle) delta[0]);
                    case "left leg" -> ((ArmorStand) entity).setLeftLegPose((EulerAngle) delta[0]);
                };
            }
        }
    }
}