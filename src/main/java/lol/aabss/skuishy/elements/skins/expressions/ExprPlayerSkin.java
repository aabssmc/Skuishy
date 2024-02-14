package lol.aabss.skuishy.elements.skins.expressions;

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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Skins - Player Skin")
@Description("Get/Sets a player's skin")
@Examples({
        "send skin of player # returns url"
})
@Since("1.4")

public class ExprPlayerSkin extends PropertyExpression<Player, String> {


    static {
        register(ExprPlayerSkin.class, String.class,
                "[minecraft] skin",
                "players"
        );
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Player[] source) {
        List<String> skins = new ArrayList<>();
        for (Player p : source){
            skins.add(p.getPlayerProfile().getTextures().getSkin().toString());
        }
        return skins.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "skin of player";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        return true;
    }
}
