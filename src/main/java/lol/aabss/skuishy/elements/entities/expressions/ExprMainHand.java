package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Player;

@SuppressWarnings("NullableProblems")
@Name("Player - Main Hand")
@Description("Gets the player's selected main hand.")
@Examples({
        "send player's main hand"
})
@Since("1.2")
public class ExprMainHand extends EntityExpression<Player, String> {

    static {
        register(ExprMainHand.class, String.class, "main[(-| )]hand", "players");
    }

    @Override
    public String get(Player player) {
        return player.getMainHand().name().toLowerCase();
    }

}
