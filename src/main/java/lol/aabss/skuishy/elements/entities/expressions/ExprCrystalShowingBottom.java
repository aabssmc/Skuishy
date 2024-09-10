package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.EnderCrystal;
import org.jetbrains.annotations.Nullable;

@Name("Ender Crystal - Showing Bottom")
@Description("Gets/sets the showing bottom state of an ender crystal.")
@Examples({
        "set showing bottom of {_crystal} to true"
})
@Since("2.8")
public class ExprCrystalShowingBottom extends EntityExpression<EnderCrystal, Boolean> {

    static {
        register(ExprCrystalShowingBottom.class, Boolean.class, "[[end[er]] crystal] showing bottom [state|mode]", "entities");
    }

    @Override
    public Boolean get(EnderCrystal enderCrystal) {
        return enderCrystal.isShowingBottom();
    }

    @Override
    public void change(EnderCrystal enderCrystal, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            enderCrystal.setShowingBottom(aBoolean);
        }
    }
}
