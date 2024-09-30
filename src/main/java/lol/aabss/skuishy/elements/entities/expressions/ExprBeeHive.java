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

@Name("Bee - Hive Location")
@Description("Gets/sets the hive location of a bee.")
@Examples({
        "set hive of {_bee} to player's location"
})
@Since("2.8")
public class ExprBeeHive extends EntityExpression<Bee, Location> {

    static {
        register(ExprBeeHive.class, Location.class, "[bee] hive location", "entities");
    }

    @Override
    public Location get(Bee bee) {
        return bee.getHive();
    }

    @Override
    public void change(Bee bee, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            bee.setHive(location);
        }
    }
}
