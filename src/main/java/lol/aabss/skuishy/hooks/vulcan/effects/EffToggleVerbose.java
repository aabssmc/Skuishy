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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Vulcan - Toggle Verbose")
@Description("Toggles verbose for a player.")
@Examples({
        "toggle verbose for player"
})
@Since("2.0")
public class EffToggleVerbose extends Effect {

    static{
        Skript.registerEffect(EffToggleVerbose.class,
                "toggle [the] [vulcan] verbose for %player%"
        );
    }

    private Expression<Player> p;

    @Override
    protected void execute(@NotNull Event e) {
        VulcanAPI api = VulcanAPI.Factory.getApi();
        Player p = this.p.getSingle(e);
        if (api != null && p != null) {
            api.toggleVerbose(p);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "toggle verbose";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        p = (Expression<Player>) exprs[0];
        return true;
    }
}
