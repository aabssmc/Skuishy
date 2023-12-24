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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("TickManager - Stop Stepping")
@Description("Makes the server stop stepping.")
@Examples({
        "stop stepping"
})
@Since("1.9")
public class EffStopStep extends Effect {
    static {
        if (Skript.classExists("org.bukkit.ServerTickManager")){
            Skript.registerEffect(EffStopStep.class,
                    "[make [[the] server|[the] game]] stop step[ping]"
            );
        }
    }

    @Override
    protected void execute(@NotNull Event e) {
        Bukkit.getServer().getServerTickManager().stopStepping();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "stop stepping";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
