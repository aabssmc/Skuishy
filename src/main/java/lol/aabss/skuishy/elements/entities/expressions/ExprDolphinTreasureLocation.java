package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.Dolphin;
import org.jetbrains.annotations.Nullable;

@Name("Dolphin - Treasure Location")
@Description("Gets/sets the treasure location of a dolphin.")
@Examples({
        "set has treasure location state of {_dolphin} to true"
})
@Since("2.8")
public class ExprDolphinTreasureLocation extends EntityExpression<Dolphin, Location> {

    static {
        register(ExprDolphinTreasureLocation.class, Location.class, "[dolphin] treasure location", "entities");
    }

    @Override
    public Location get(Dolphin dolphin) {
        return dolphin.getTreasureLocation();
    }

    @Override
    public void change(Dolphin dolphin, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            dolphin.setTreasureLocation(location);
        }
    }

}