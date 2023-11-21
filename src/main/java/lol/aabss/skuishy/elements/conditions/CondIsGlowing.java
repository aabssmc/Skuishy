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

@Name("Player - Is Glowing")
@Description("Checks whether a player is glowing.")
@Examples({
        "if player is glowing:",
        "\tmake player stop glowing"
})
@Since("1.6")

public class CondIsGlowing extends Condition {

    static{
        Skript.registerCondition(CondIsGlowing.class,
                "%players% (is|are) glowing",
                "%players% (isn't|is not|aren't|are not) glowing"
        );
    }

    Expression<Player> player;
    boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        Player p = player.getSingle(e);
        assert p != null;
        if (is){
            return p.isGlowing();
        }
        return !p.isGlowing();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return player.toString(e,debug) + " is glowing";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        is = (matchedPattern == 0);
        return true;
    }
}
