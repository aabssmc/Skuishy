package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.generator.structure.Structure;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("World - Nearest Structure")
@Description("Locates the nearest structure in a specified radius and location.")
@Examples({
        "send nearest ocean monument in radius 1000 around player"
})
@Since("1.7")

public class ExprLocateStructure extends SimpleExpression<Location> {

    static{
        Skript.registerExpression(ExprLocateBiome.class, Location.class, ExpressionType.COMBINED,
                "nearest [:unexplored] [structure] %generatedstructure% in radius %integer% around %location%"
        );
    }

    Expression<Structure> structure;
    Expression<Integer> radius;
    Expression<Location> location;
    boolean unexplored;

    @Override
    protected @Nullable Location[] get(@NotNull Event e) {
        Location loc = location.getSingle(e);
        Structure struc = structure.getSingle(e);
        Integer rad = radius.getSingle(e);
        assert loc != null; assert struc != null; assert  rad != null;
        return new Location[]{loc.getWorld().locateNearestStructure(loc, struc, rad, unexplored).getLocation()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "locate structure";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        structure = (Expression<Structure>) exprs[0];
        radius = (Expression<Integer>) exprs[1];
        location = (Expression<Location>) exprs[2];
        unexplored = parseResult.hasTag("unexplored");
        return true;
    }
}
