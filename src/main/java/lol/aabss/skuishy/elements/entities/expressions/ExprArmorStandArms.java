package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Armor Stand - Arms")
@Description("Gets/sets the arms state of an armor stand.")
@Examples({
        "set arms state of {_armorstand} to true"
})
@Since("2.8")
public class ExprArmorStandArms extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprArmorStandArms.class, Boolean.class, "[armor[ |-]stand] arms [mode|state]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "arms";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof ArmorStand) {
            return ((ArmorStand) entity).hasArms();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Boolean) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof ArmorStand) {
                        ((ArmorStand) entity).setArms((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}
