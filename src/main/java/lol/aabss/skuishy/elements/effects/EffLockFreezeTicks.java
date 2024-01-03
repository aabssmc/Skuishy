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
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Entity - Lock Freeze Ticks")
@Description("Locks/Unlocks freeze ticks of player")
@Examples({
        "lock freeze ticks of player"
})
@Since("1.9")

public class EffLockFreezeTicks extends Effect {

    static{
        Skript.registerEffect(EffLockFreezeTicks.class,
                "[:un]lock (freeze|frozen) ticks of %entities%"
        );
    }

    private Expression<Entity> entity;
    private Boolean lock;

    @Override
    protected void execute(@NotNull Event e) {
        for (Entity entity : this.entity.getArray(e)){
            entity.lockFreezeTicks(lock);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "unlock freeze ticks";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        lock = !parseResult.hasTag("un");
        entity = (Expression<Entity>) exprs[0];
        return true;
    }
}
