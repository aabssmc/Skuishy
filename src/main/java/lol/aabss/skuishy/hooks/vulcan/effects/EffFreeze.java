package lol.aabss.skuishy.hooks.vulcan.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Vulcan - Freeze Player")
@Description("Freezes/Unfreezes a player with vulcan.")
@Examples({
        "vulcan freeze player",
        "wait 10 seconds",
        "vulcan unfreeze player"
})
@Since("2.1")
public class EffFreeze extends Effect {

    static {
        Skript.registerEffect(EffFreeze.class,
                "[vulcan] [:un]freeze %players%"
        );
    }

    private Expression<Player> p;
    private boolean freeze;

    @Override
    protected void execute(@NotNull Event e) {
        for (Player p : this.p.getArray(e)){
            VulcanAPI.Factory.getApi().setFrozen(p, freeze);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "freeze player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            p = (Expression<Player>) exprs[0];
            freeze = !parseResult.hasTag("un");
            return true;
        }
        Skript.error("You can not use Vulcan syntax!");
        return false;
    }
}
