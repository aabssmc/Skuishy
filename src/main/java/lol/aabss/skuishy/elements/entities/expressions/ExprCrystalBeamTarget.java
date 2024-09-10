package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.jetbrains.annotations.Nullable;

@Name("Ender Crystal - Beam Target")
@Description("Gets/sets the beam target of an ender crystal.")
@Examples({
        "set beam target of {_crystal} to player"
})
@Since("2.8")
public class ExprCrystalBeamTarget extends EntityExpression<EnderCrystal, Location> {

    static {
        register(ExprCrystalBeamTarget.class, Location.class, "[end[er] crystal] beam target", "entities");
    }

    @Override
    public Location get(EnderCrystal enderCrystal) {
        return enderCrystal.getBeamTarget();
    }

    @Override
    public void change(EnderCrystal enderCrystal, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            enderCrystal.setBeamTarget(location);
        }
    }
}