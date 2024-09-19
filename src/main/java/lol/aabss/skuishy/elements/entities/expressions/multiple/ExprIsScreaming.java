package lol.aabss.skuishy.elements.entities.expressions.multiple;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

@Name("Enderman/Goat - Is Screaming")
@Description("Gets/sets the is screaming state of an enderman or goat.")
@Examples({
        "set screaming state of {_enderman} to true"
})
@Since("2.8")
public class ExprIsScreaming extends EntityExpression<LivingEntity, Boolean> {

    static {
        register(ExprIsScreaming.class, Boolean.class, "[enderman|goat] screaming [state|mode]", "entities");
    }

    @Override
    public Boolean get(LivingEntity entity) {
        if (entity instanceof Enderman) {
            return ((Enderman) entity).isScreaming();
        } else if (entity instanceof Goat) {
            return ((Goat) entity).isScreaming();
        }
        return null;
    }

    @Override
    public void change(LivingEntity entity, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            if (entity instanceof Enderman) {
                ((Enderman) entity).setScreaming(aBoolean);
            } else if (entity instanceof Goat) {
                ((Goat) entity).setScreaming(aBoolean);
            }
        }
    }
}