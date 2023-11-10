package lol.aabss.skuishy.elements.expressions.skins;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import lol.aabss.skuishy.other.skins.SkinWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Skins - Player Skin")
@Description("Get/Sets a player's skin")
@Examples({
        "set skin of player to \"Dinnerbone\"\n#Also sets the cape"
})
@Since("1.4")

public class ExprPlayerSkin extends PropertyExpression<Player, String> {


    static {
        register(ExprPlayerSkin.class, String.class,
                "[minecraft] skin",
                "players"
        );
    }

    private Expression<Player> player;

    @Override
    protected String @NotNull [] get(@NotNull Event event, Player @NotNull [] source) {
        String skin = source[0].getPlayerProfile().getName();
        return new String[] {skin};
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        Player p = player.getSingle(event);
        if (mode == Changer.ChangeMode.SET) {
            assert p != null;
            SkinWrapper.setSkin(p, (String) delta[0]);

        }
        else if (mode == Changer.ChangeMode.RESET) {
            assert p != null;
            SkinWrapper.setSkin(p, p.getName());
        } else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        else if (mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(String.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return Classes.getDebugMessage(getExpr()) + " cape ";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
