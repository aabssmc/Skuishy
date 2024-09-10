package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.Nullable;

@Name("Armor Stand - Baseplate")
@Description("Gets/sets the baseplate state of an armor stand.")
@Examples({
        "set baseplate state of {_armorstand} to true"
})
@Since("2.8")
public class ExprArmorStandBaseplate extends EntityExpression<ArmorStand, Boolean> {

    static {
        register(ExprArmorStandBaseplate.class, Boolean.class, "[armor[ |-]stand] baseplate [mode|state]", "entities");
    }

    @Override
    public Boolean get(ArmorStand armorStand) {
        return armorStand.hasBasePlate();
    }

    @Override
    public void change(ArmorStand armorStand, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            armorStand.setBasePlate(aBoolean);
        }
    }

}

