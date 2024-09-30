package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Flower Location")
@Description("Gets/sets the flower location of a bee.")
@Examples({
        "set flower of {_bee} to location of {_flower}"
})
@Since("2.8")
public class ExprBeeFlower extends EntityExpression<Bee, Location> {

    static {
        register(ExprBeeFlower.class, Location.class, "[bee] flower location", "entities");
    }

    @Override
    public Location get(Bee bee) {
        return bee.getFlower();
    }

    @Override
    public void change(Bee bee, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            bee.setFlower(location);
        }
    }
}
