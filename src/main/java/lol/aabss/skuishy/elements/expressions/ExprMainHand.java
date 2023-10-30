package lol.aabss.skuishy.elements.expressions;

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
import org.jetbrains.annotations.NotNull;

@Name("Player - Main Hand")
@Description("Gets the player's selected main hand.")
@Examples({
        "send player's main hand"
})
@Since("1.2")
public class ExprMainHand extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprMainHand.class, String.class, ExpressionType.PROPERTY, "[the] main[(-| )]hand of %player%", "%player%'s main[(-| )]hand");
    }

    private Expression<Player> player;

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parser) {
        if (exprs.length == 0){
            return false;
        }
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "Main hand";
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event) {
        Player p = player.getSingle(event);
        assert p != null;
        return new String[]{p.getMainHand().toString().toLowerCase()};
    }
}
