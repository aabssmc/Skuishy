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
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Vulcan - Toggle Verbose")
@Description("Toggles verbose for a player.")
@Examples({
        "toggle verbose for player"
})
@Since("2.0")
public class EffBanWave extends Effect {

    static{
        Skript.registerEffect(EffBanWave.class,
                "[execute [the]] [vulcan] ban wave"
        );
    }

    @Override
    protected void execute(@NotNull Event e) {
        VulcanAPI api = VulcanAPI.Factory.getApi();
        if (api != null) {
            api.executeBanWave();
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "execute ban wave";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
