package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

@Name("Ender Dragon - Podium")
@Description("Gets/sets the podium of an ender dragon.")
@Examples({
        "set podium of {_dragon} to player's location"
})
@Since("2.8")
public class ExprEnderDragonPodium extends EntityExpression<EnderDragon, Location> {

    static {
        register(ExprEnderDragonPodium.class, Location.class, "[ender] dragon podium", "entities");
    }

    @Override
    public Location get(EnderDragon enderDragon) {
        return enderDragon.getPodium();
    }

    @Override
    public void change(EnderDragon enderDragon, Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            enderDragon.setPodium(location);
        }
    }
}
