package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Llama - Caravan")
@Description("Makes a llama join/leave a caravan..")
@Examples({
        "make {_llama} leave caravan"
})
@Since("2.8")
public class EffCaravan extends Effect {

    static {
        Skript.registerEffect(EffCaravan.class,
                "make %entities% join %entity% caravan",
                "make %entities% leave caravan"
        );
    }

    private Expression<Entity> llama;
    private Expression<Entity> caravan;

    @Override
    protected void execute(@NotNull Event e) {
        Llama caravan = null;
        if (this.caravan != null) {
            Entity entity = this.caravan.getSingle(e);
            if (entity instanceof Llama) {
                caravan = (Llama) entity;
            }
        }
        for (Entity entity : llama.getArray(e)) {
            if (entity instanceof Llama) {
                if (caravan == null) {
                    ((Llama) entity).leaveCaravan();
                } else {
                    ((Llama) entity).joinCaravan(caravan);
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "llama caravan";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        llama = (Expression<Entity>) exprs[0];
        if (matchedPattern == 0) caravan = (Expression<Entity>) exprs[1];
        return true;
    }
}
