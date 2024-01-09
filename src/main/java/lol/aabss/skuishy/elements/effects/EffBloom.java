package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.SculkCatalyst;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EffBloom extends Effect {

    static{
        if (Skript.classExists("org.bukkit.block.SculkCatalyst")){
            Skript.registerEffect(EffBloom.class,
                    "[force[fully]] bloom %block% with (charge|power) %integer% [at %-location%]"
            );
        }
    }

    private Expression<Integer> charge;
    private Expression<Block> block;
    private Expression<Location> location;

    @Override
    protected void execute(@NotNull Event e) {
        if (location != null && block != null && charge != null) {
            Location loc = location.getSingle(e);
            Block block = this.block.getSingle(e);
            Integer charge = this.charge.getSingle(e);
            if (charge != null) {
                if (block instanceof SculkCatalyst b) {
                    if (loc != null) {
                        b.bloom(loc, charge);
                    } else {
                        b.bloom(block, charge);
                    }
                }
            }
        }
    }


    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "bloom location";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        charge = (Expression<Integer>) exprs[1];
        location = (Expression<Location>) exprs[2];
        return true;
    }
}
