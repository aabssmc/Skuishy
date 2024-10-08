package lol.aabss.skuishy.elements.vulcan.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.elements.vulcan.VulcanHook;
import me.frep.vulcan.api.VulcanAPI$Factory;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Vulcan - Toggle Verbose")
@Description("Toggles verbose for a player.")
@Examples({
        "toggle verbose for player"
})
@Since("2.0")
public class EffBanWave extends Effect {

    static{
        if (VulcanHook.vulcanEnabled()) {
            Skript.registerEffect(EffBanWave.class,
                    "[execute [the]] [vulcan] ban wave"
            );
        }
    }

    @Override
    protected void execute(@NotNull Event event) {
        VulcanAPI$Factory.getApi().executeBanWave();
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "execute ban wave";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
