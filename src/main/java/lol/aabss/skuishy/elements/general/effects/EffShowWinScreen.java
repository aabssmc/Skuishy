package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;
@Name("Player - Show Win Screen")
@Description("Shows the win screen to a player.")
@Examples({
        "show win screen to event-player"
})
@Since("2.0")
public class EffShowWinScreen extends Effect {

    static{
        if (Skript.methodExists(Player.class, "showWinScreen")) {
            Skript.registerEffect(EffShowWinScreen.class,
                    "show [the] win screen to %players%",
                    "make %players% see the win screen"
            );
        }
    }

    private Expression<Player> p;

    @Override
    protected void execute(@NotNull Event e) {
        Player[] p = this.p.getArray(e);
        for (Player player : p) {
            player.showWinScreen();
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "show win screen";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        p = (Expression<Player>) exprs[0];
        return true;
    }
}
