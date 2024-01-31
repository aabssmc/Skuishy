package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Jukebox - Eject Record")
@Description("Ejects the current record from the music disc.")
@Examples({
        "eject disc from target block"
})
@Since("2.1")
public class EffEjectRecord extends Effect {
    static {
        Skript.registerEffect(EffEjectRecord.class,
                "eject (record|dis(c|k)) from %blocks%"
        );
    }

    private Expression<Block> block;

    @Override
    protected void execute(@NotNull Event e) {
        for (Block b : block.getArray(e)){
            if (b instanceof Jukebox){
                ((Jukebox) b).eject();
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "eject record";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        return true;
    }
}
