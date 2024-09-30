package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.Vex;

import javax.annotation.Nullable;

@Name("Vex - Bound Location")
@Description("Gets/sets the bound location of a vex.")
@Examples({
        "set bound location of {_vex} to player's location"
})
@Since("2.8")
public class ExprVexBound extends EntityExpression<Vex, Location> {

    static {
        register(ExprVexBound.class, Location.class, "[vex] bound location", "entities");
    }

    @Override
    public Location get(Vex vex) {
        return vex.getBound();
    }

    @Override
    public void change(Vex vex, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            vex.setBound(location);
        }
    }
}
