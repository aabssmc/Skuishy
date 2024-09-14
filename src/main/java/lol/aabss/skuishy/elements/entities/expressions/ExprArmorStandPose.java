package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

@Name("Armor Stand - Poses")
@Description("Gets/sets the poses of an armor stand.")
@Examples({
        "set {_angle} to head pose of {_armorstand}"
})
@Since("2.8")
public class ExprArmorStandPose extends EntityExpression<ArmorStand, Vector> {

    static {
        register(ExprArmorStandPose.class, Vector.class,
                "(:(head|torso|right arm|left arm|right leg|left leg)) pose",
                "entities"
        );
    }

    @Override
    public Vector get(ArmorStand armorStand) {
        return switch (tags.getFirst()) {
            case "head" -> toVector(armorStand.getHeadPose());
            case "torso" -> toVector(armorStand.getBodyPose());
            case "right arm" -> toVector(armorStand.getRightArmPose());
            case "left arm" -> toVector(armorStand.getLeftArmPose());
            case "right leg" -> toVector(armorStand.getRightLegPose());
            case "left leg" -> toVector(armorStand.getLeftLegPose());
            default -> null;
        };
    }

    @Override
    public void change(ArmorStand armorStand, @Nullable Vector vector, Changer.ChangeMode mode) {
        if (vector != null && mode == Changer.ChangeMode.SET) {
            switch (tags.getFirst()) {
                case "head" -> armorStand.setHeadPose(fromVector(vector));
                case "torso" -> armorStand.setBodyPose(fromVector(vector));
                case "right arm" -> armorStand.setRightArmPose(fromVector(vector));
                case "left arm" -> armorStand.setLeftArmPose(fromVector(vector));
                case "right leg" -> armorStand.setRightLegPose(fromVector(vector));
                case "left leg" -> armorStand.setLeftLegPose(fromVector(vector));
            }
        }
    }

    private Vector toVector(EulerAngle angle) {
        return new Vector(angle.getX(), angle.getY(), angle.getZ());
    }

    private EulerAngle fromVector(Vector vector) {
        return new EulerAngle(vector.getX(), vector.getY(), vector.getZ());
    }

}