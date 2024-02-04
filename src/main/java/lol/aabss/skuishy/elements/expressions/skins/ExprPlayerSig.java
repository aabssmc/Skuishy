package lol.aabss.skuishy.elements.expressions.skins;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.skins.SkinWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

@Name("Skins - Player Skin Signature")
@Description("Sends the signature of the player's skin .")
@Examples({
        "command send-signature <player>:",
        "\ttrigger:",
        "\t\tsend arg-1's skin signature"
})
@Since("1.0")

public class ExprPlayerSig extends PropertyExpression<Player, String> {

    static {
        register(ExprPlayerSig.class, String.class,
                "(texture|skin) signature",
                "players"
        );
    }


    @Override
    protected String @NonNull [] get(@NonNull Event event, Player @NonNull [] source) {
        Player p = source[0] != null ? source[0] : null;
        assert p != null;
        return new String[]{SkinWrapper.getProfileProperties(p).getSignature()};
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(Event event, boolean debug) {
        return Classes.getDebugMessage(getExpr()) + " Skin Signature ";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, @NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}

