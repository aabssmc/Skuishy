package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.Phantom;
import org.jetbrains.annotations.Nullable;

@Name("Phantom - Anchor Location")
@Description("Gets/sets the anchor location of a phantom.")
@Examples({
        "set phantom anchor location of {_phantom} to player's location"
})
@Since("2.8")
public class ExprPhantomAnchorLocation extends EntityExpression<Phantom, Location> {

    static {
        register(ExprPhantomAnchorLocation.class, Location.class, "[phantom] anchor location", "entities");
    }

    @Override
    public Location get(Phantom phantom) {
        return phantom.getAnchorLocation();
    }

    @Override
    public void change(Phantom phantom, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            phantom.setAnchorLocation(location);
        }
    }
}