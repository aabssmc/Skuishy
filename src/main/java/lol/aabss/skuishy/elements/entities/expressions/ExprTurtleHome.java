package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Location;
import org.bukkit.entity.Turtle;
import org.jetbrains.annotations.Nullable;

@Name("Turtle - Home")
@Description("Gets/Sets the home of a turtle.")
@Examples({
        "set turtle home of last spawned turtle to player's location"
})
@Since("2.8")
public class ExprTurtleHome extends EntityExpression<Turtle, Location> {

    static {
        register(ExprTurtleHome.class, Location.class, "turtle home", "entities");
    }

    @Override
    public Location get(Turtle turtle) {
        return turtle.getHome();
    }

    @Override
    public void change(Turtle turtle, @Nullable Location location, Changer.ChangeMode mode) {
        if (location != null && mode == Changer.ChangeMode.SET) {
            turtle.setHome(location);
        }
    }
}
