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
import org.jetbrains.annotations.NotNull;

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
        register(ExprPlayerSig.class, String.class, "(texture|skin) signature", "players");
    }


    @Override
    protected String @NotNull [] get(@NotNull Event event, Player @NotNull [] source) {
        Player p = source[0] != null ? source[0] : null;
        assert p != null;
        return new String[]{SkinWrapper.getProfileProperties(p).getSignature()};
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return Classes.getDebugMessage(getExpr()) + " Skin Signature ";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}

