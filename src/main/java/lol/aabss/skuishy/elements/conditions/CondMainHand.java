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


@Name("Player - Main Hand")
@Description("Checks the player's main hand.")
@Examples({
        "on join:",
        "\tif player's main hand is left:",
        "\t\tsend \"you are not normal\" to player"
})
@Since("1.2")
public class CondMainHand extends Condition {
    enum MainHandSide {
        LEFT, RIGHT, UNKNOWN;
    }


    static {
        Skript.registerCondition(CondMainHand.class,
                "%players%'s main hand (0:is|1:is( not|n't)) (:left|:right)",
                "main hand of %players% (0:is|1:is( not|n't)) (:left|:right)"
        );
    }

    Expression<Player> player;

    MainHandSide side;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        boolean negated = parser.mark == 1;
        if (parser.hasTag("right")) side = MainHandSide.RIGHT;
        else if (parser.hasTag("left")) side = MainHandSide.LEFT;
        setNegated(negated);
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "main hand of " + player.toString(event, debug) + " is " + side.toString();
    }

    @Override
    public boolean check(@NotNull Event event) {
        Player p = player.getSingle(event);
        assert p != null;
        String mh = p.getMainHand().toString();

        if (side == MainHandSide.LEFT && MainHandSide.valueOf(mh) == MainHandSide.LEFT)
            return !isNegated();
        else if (side == MainHandSide.RIGHT && MainHandSide.valueOf(mh) == MainHandSide.RIGHT)
            return !isNegated();

        return isNegated();
    }
}
