package lol.aabss.skuishy.elements.expressions.skins;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

@Name("Skins - Player Skin Texture (URL)")
@Description("Gets the player's skin as a url.")
@Examples({
        "command send-texture <player>:",
        "\ttrigger:",
        "\t\tsend texture url of arg-1"
})
@Since("1.0")

public class ExprPlayerTexURL extends PropertyExpression<Player, String> {

    static {
        register(ExprPlayerTexURL.class, String.class,
                "[skin] texture url",
                "players"
        );
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, @NonNull ParseResult parser) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }

    @Override
    public @NonNull String toString(Event event, boolean debug) {
        return getExpr().toString(event, debug) + " Skin Texture URL";
    }

    @Override
    protected String @NonNull [] get(@NonNull Event event, Player @NonNull [] source) {
        try {
            if (source.length < 1) return new String[0];
            var player = source[0];
            return new String[] {player.getPlayerProfile().getTextures().getSkin().toString()};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}