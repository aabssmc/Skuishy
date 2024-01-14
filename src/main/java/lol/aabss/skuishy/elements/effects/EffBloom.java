package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.block.SculkCatalyst;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EffBloom extends Effect {

    static{
        if (Skript.methodExists(SculkCatalyst.class, "bloom", Block.class, Integer.class)){
            Skript.registerEffect(EffBloom.class,
                    "[force[fully]] bloom %blocks% with (charge|power) %integer%",
                    "make %blocks% bloom with (charge|power) %integer%"
            );
        }
    }

    private Expression<Integer> charge;
    private Expression<Block> block;

    @Override
    protected void execute(@NotNull Event e) {
        Block[] block = this.block.getArray(e);
        Integer charge = this.charge.getSingle(e);
        if (charge != null) {
            for (Block b : block) {
                if (b instanceof SculkCatalyst bb) {
                    bb.bloom(b, charge);
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
        return true;
    }
}
