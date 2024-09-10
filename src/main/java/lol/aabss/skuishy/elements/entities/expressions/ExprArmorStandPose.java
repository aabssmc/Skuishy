package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.Nullable;

@Name("Armor Stand - Poses")
@Description("Gets/sets the poses of an armor stand.")
@Examples({
        "set {_angle} to head pose of {_armorstand}"
})
@Since("2.8")
public class ExprArmorStandPose extends EntityExpression<ArmorStand, EulerAngle> {

    static {
        register(ExprArmorStandPose.class, EulerAngle.class,
                "(:(head|torso|right arm|left arm|right leg|left leg)) pose",
                "entities"
        );
    }

    @Override
    public EulerAngle get(ArmorStand armorStand) {
        return switch (tags.getFirst()) {
            case "head" -> armorStand.getHeadPose();
            case "torso" -> armorStand.getBodyPose();
            case "right arm" -> armorStand.getRightArmPose();
            case "left arm" -> armorStand.getLeftArmPose();
            case "right leg" -> armorStand.getRightLegPose();
            case "left leg" -> armorStand.getLeftLegPose();
            default -> null;
        };
    }

    @Override
    public void change(ArmorStand armorStand, @Nullable EulerAngle eulerAngle, Changer.ChangeMode mode) {
        if (eulerAngle != null && mode == Changer.ChangeMode.SET) {
            switch (tags.getFirst()) {
                case "head" -> armorStand.setHeadPose(eulerAngle);
                case "torso" -> armorStand.setBodyPose(eulerAngle);
                case "right arm" -> armorStand.setRightArmPose(eulerAngle);
                case "left arm" -> armorStand.setLeftArmPose(eulerAngle);
                case "right leg" -> armorStand.setRightLegPose(eulerAngle);
                case "left leg" -> armorStand.setLeftLegPose(eulerAngle);
            };
        }
    }

}