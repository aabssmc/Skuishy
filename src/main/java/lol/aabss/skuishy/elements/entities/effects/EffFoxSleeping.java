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
import org.bukkit.entity.Fox;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Fox - Sleep State")
@Description("Sets the sleeping state of a fox.")
@Examples({
        "set fox sleep state of {_fox} to true"
})
@Since("2.8")
public class EffFoxSleeping extends Effect {

    static {
        Skript.registerEffect(EffFoxSleeping.class,
                "set [fox] sleep[ing] [state|mode] of %entities% to %boolean%",
                "set %entities%'[s] [fox] sleep[ing] [state|mode] to %boolean%"
        );
    }

    private Expression<Entity> entities;
    private Expression<Boolean> bool;

    @Override
    protected void execute(Event event) {
        for (Entity entity : entities.getArray(event)) {
            if (entity instanceof Fox) {
                ((Fox) entity).setSleeping(this.bool.getSingle(event));
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "sleep state";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        bool = (Expression<Boolean>) exprs[1];
        return true;
    }
}
