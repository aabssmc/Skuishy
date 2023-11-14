package lol.aabss.skuishy.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


@Name("Player - Glowing")
@Description("Checks if the player is glowing")
@Examples({
        "on leave:",
        "\tif player is glowing:",
        "\t\tmake player stop glowing"
})
@Since("1.5")
public class CondIsGlowing extends Condition {

    static {
        Skript.registerCondition(CondMainHand.class,
                "%players%'s (0:is|1:is( not|n't)) glowing"
        );
    }

    Expression<Player> player;
    boolean is;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        is = parser.hasTag("is");
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return player.toString(event, debug) + " is glowing";
    }

    @Override
    public boolean check(@NotNull Event event) {
        Player p = player.getSingle(event);
        assert p != null;
        return is ? p.isGlowing() : !p.isGlowing();
    }
}
