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

import java.util.ArrayList;
import java.util.List;

import static lol.aabss.skuishy.other.skins.SkinWrapper.skinname;

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

    @Override
    protected String @NotNull [] get(@NotNull Event event, Player @NotNull [] source) {
        List<String> skins = new ArrayList<>();
        for (Player p : source) {
            skins.add(skinname.get(p) == null ? p.getName() : skinname.get(p));
        }
        return skins.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        for (Player p : getExpr().getArray(event)) {
            if (mode == Changer.ChangeMode.SET) {
                SkinWrapper.setSkin(p, (String) delta[0]);
            } else if (mode == Changer.ChangeMode.RESET) {
                SkinWrapper.setSkin(p, p.getName());
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return Classes.getDebugMessage(getExpr()) + " skin";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }
}
