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

@Name("Player - Wake Up")
@Description("Makes a player wake up and optionally sets their spawn.")
@Examples({
        "make player wake up and set their spawn"
})
@Since("2.0")
public class EffWakeUp extends Effect {

    static {
        Skript.registerEffect(EffWakeUp.class,
                "make %players% wake up [spawn:and set the[ir] spawn [location]]"
        );
    }

    boolean spawn;
    Expression<Player> player;

    @Override
    protected void execute(@NotNull Event e) {
        Player[] p = player.getArray(e);
        for (Player player : p){
            player.wakeup(spawn);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "make player wakeup";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        spawn = parseResult.hasTag("spawn");
        player = (Expression<Player>) exprs[0];
        return false;
    }
}
