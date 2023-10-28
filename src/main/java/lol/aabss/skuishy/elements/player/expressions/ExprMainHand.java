package lol.aabss.skuishy.elements.player.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Player - Main Hand")
@Description("Gets the player's selected main hand.")
@Examples({
        "send player's main hand"
})
@Since("1.2")
public class ExprMainHand extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprMainHand.class, String.class, ExpressionType.COMBINED, "[the] main[(-| )]hand of %player%", "%player%'s main[(-| )]hand");
    }

    private Expression<Player> player;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    @Nullable
    public String toString(Event event, boolean debug) {
        return "Main hand";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        Player p = player.getSingle(event);
        assert p != null;
        return new String[]{p.getMainHand().toString().toLowerCase()};
    }
}
