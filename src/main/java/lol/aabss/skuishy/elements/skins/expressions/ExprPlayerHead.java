package lol.aabss.skuishy.elements.skins.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.SkinWrapper;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Skins - Player Head")
@Description("Returns a player's head as a string.")
@Examples({
        "send face of \"aabss\" # works with strings too",
        "send no layer face of \"aabss\" # sends face without the outer layer"
})
@Since("2.6")
public class ExprPlayerHead extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprPlayerHead.class, String.class,
                "[without:no [outer] layer] (head|face)",
                "offlineplayers/strings"
        );
    }

    boolean without;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        without = parseResult.hasTag("without");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "player head";
    }

    @Override
    public @Nullable String convert(Object player) {
        if (player instanceof OfflinePlayer){
            return SkinWrapper.sendHead(((OfflinePlayer) player).getName(), !without);
        }
        return SkinWrapper.sendHead(String.valueOf(player), !without);
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }
}
