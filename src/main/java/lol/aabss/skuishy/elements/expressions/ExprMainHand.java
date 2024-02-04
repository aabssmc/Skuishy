package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("Player - Main Hand")
@Description("Gets the player's selected main hand.")
@Examples({
        "send player's main hand"
})
@Since("1.2")
public class ExprMainHand extends PropertyExpression<Player, String> {

    static {
        register(ExprMainHand.class, String.class,
                "main[(-| )]hand",
                "players"
        );
    }


    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parser) {
        setExpr((Expression<Player>) exprs[0]);
        return true;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "main hand";
    }

    @Override
    protected @Nullable String[] get(@NotNull Event event, Player[] source) {
        for (Player p : source) {
            return new String[]{p.getMainHand().toString().toLowerCase()};
        }
        return new String[]{};
    }
}
