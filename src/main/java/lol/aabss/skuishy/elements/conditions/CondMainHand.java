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
import java.util.Objects;


    @Name("Player - Main Hand")
    @Description("Checks the player's main hand.")
    @Examples({
            "on join:",
            "\tif player's main hand is left:",
            "\t\tsend \"you are not normal\" to player"
    })
    @Since("1.2")
    public class CondMainHand extends Condition {

        static {
            Skript.registerCondition(CondMainHand.class, "(%player%'s main[(-| )]hand|main[(-| )]hand of %player%) (is|are) (:right|:left)", "(%player%'s main[(-| )]hand|main[(-| )]hand of %player%) (isn't|is not|aren't|are not) (:right|:left)");
        }

        Expression<Player> player;

        int matpat;

        String side;

        @SuppressWarnings("unchecked")
        @Override
        public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parser) {
            this.player = (Expression<Player>) expressions[0];
            matpat = matchedPattern;
            if (parser.hasTag("right")){
                side = "right";
            }
            else if (parser.hasTag("left")){
                side = "left";
            }
            return true;
        }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "main hand of " + player.toString(event, debug);
    }

    @Override
    public boolean check(@NotNull Event event) {
        Player p = player.getSingle(event);
        assert p != null;
        String mh = p.getMainHand().toString().toLowerCase();
        if (Objects.equals(side, "right")){
            if (matpat == 0){
                return mh.equals("right");
            }
            else{
                return !mh.equals("right");
            }
        }
        else if (Objects.equals(side, "left")){
            if (matpat == 0){
                return mh.equals("left");
            }
            else{
                return !mh.equals("left");
            }
        }
        return false;
    }
}
