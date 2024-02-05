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
import org.bukkit.block.Biome;
import org.bukkit.event.Event;
import org.bukkit.util.BiomeSearchResult;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("World - Nearest Biome")
@Description("Locates the nearest biome in a specified radius and location.")
@Examples({
        "send nearest plains in radius 1000 around player"
})
@Since("1.7")

public class ExprLocateBiome extends SimpleExpression<Location> {

    static{
        Skript.registerExpression(ExprLocateBiome.class, Location.class, ExpressionType.COMBINED,
                "nearest [biome] %biome% in radius %integer% around %location%"
        );
    }

    private Expression<Biome> biome;
    private Expression<Integer> radius;
    private Expression<Location> location;

    @Override
    protected @Nullable Location[] get(@NotNull Event e) {
        Location loc = location.getSingle(e);
        Biome bio = biome.getSingle(e);
        Integer rad = radius.getSingle(e);
        if (loc != null && bio != null && rad != null) {
            if (Skript.classExists("org.bukkit.util.BiomeSearchResult")) {
                BiomeSearchResult r = loc.getWorld().locateNearestBiome(loc, rad, bio);
                if (r != null) {
                    return new Location[]{r.getLocation()};
                }
            } else{
                Location r = loc.getWorld().locateNearestBiome(loc, bio, rad);
                if (r != null){
                    return new Location[]{r};
                }
            }
        }
        return new Location[]{null};
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
        return "nearest biome";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        biome = (Expression<Biome>) exprs[0];
        radius = (Expression<Integer>) exprs[1];
        location = (Expression<Location>) exprs[2];
        return true;
    }
}
