package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.jetbrains.annotations.Nullable;

@Name("Bat - Target Location")
@Description("Gets/sets the target location of a bat.")
@Examples({
        "set target location of {_bat} to player's location"
})
@Since("2.8")
public class ExprBatTargetLocation extends EntityExpression<Bat, Location> {

    static {
        register(ExprBatTargetLocation.class, Location.class, "[bat] target location", "entities");
    }

    @Override
    public Location get(Bat bat) {
        return bat.getTargetLocation();
    }

    @Override
    public void change(Bat bat, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            bat.setTargetLocation(location);
        }
    }
}

